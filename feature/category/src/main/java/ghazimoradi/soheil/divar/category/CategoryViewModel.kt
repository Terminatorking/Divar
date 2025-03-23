package ghazimoradi.soheil.divar.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.usecases.GetCategoriesUseCase
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : BaseViewModel<CategoryUiState, CategoryUiEvent>() {

    init {
        getCategories()
    }

    override fun createInitialState() = CategoryUiState()

    override fun onTriggerEvent(event: CategoryUiEvent) {
        when (event) {
            is CategoryUiEvent.OnCategorySelected -> {
                if (event.category.children.isNotEmpty()) {
                    val newList = currentState.selectedCategories.toMutableList()
                    newList.add(event.category)
                    setState {
                        copy(selectedCategories = newList.toImmutableList())
                    }
                    handleShowingCategory()
                } else {
//                    setState {
//                        copy(selectedCategory = event.category)
//                    }
//                    saveFilter(event.category)
                }
            }

            CategoryUiEvent.OnBackInCategoryDialog -> {
                if (currentState.selectedCategories.isNotEmpty()) {
                    val newList: MutableList<Category> =
                        currentState.selectedCategories.toMutableList()
                    newList.removeAt(newList.size - 1)
                    setState {
                        copy(selectedCategories = newList.toImmutableList())
                    }
                    handleShowingCategory()
                }
            }

            CategoryUiEvent.OnLoadMore -> {
            }

            CategoryUiEvent.OnRefresh -> {
                getCategories()
            }

            CategoryUiEvent.OnClearSelectedCategory -> {
                setState {
                    copy(selectedCategory = null)
                }
            }
        }
    }

    private fun getCategories() {
        setState {
            copy(isRefreshing = true)
        }

        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect { dataResult: DataResult<List<Category>> ->
                dataResult.onSuccess { categoriesList ->
                    setState {
                        currentState.copy(
                            isRefreshing = false,
                            categories = categoriesList.toImmutableList()
                        )
                    }
                    handleShowingCategory()
                }.onFailure { apiError ->
                    setState {
                        copy(isRefreshing = false)
                    }
                    setUiMessage(UIMessage(stringValue = "مشکلی پیش امده اینترنت خود را چک کنید اگر اینترنت متصل بود ایراد سرور است صبور باشید"))
                    apiError.eLog(tag = "serverError")
                }
            }
        }
    }

    private fun handleShowingCategory() {
        if (currentState.selectedCategories.isEmpty()) {
            setState {
                copy(
                    showCategories = currentState.categories,
                    categoryTitle = null
                )
            }
        } else {
            setState {
                copy(
                    showCategories = currentState.selectedCategories.last().children.toImmutableList(),
                    categoryTitle = currentState.selectedCategories.last().name
                )
            }
        }
    }
}