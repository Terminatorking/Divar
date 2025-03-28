package ghazimoradi.soheil.divar.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.usecases.category.GetCategoriesUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.SaveFilterFromCategoryUseCase
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase
) : BaseViewModel<CategoryUiState, CategoryUiEvent>() {

    init {
        getCategories()
    }

    private fun getCategories() {
        setState { copy(isRefreshing = true) }
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        currentState.copy(
                            isRefreshing = false,
                            categories = it.toImmutableList(),
                        )
                    }
                    handleShowingCategory()
                }.onFailure { apiError ->
                    setState { copy(isRefreshing = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
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
                    setState {
                        copy(selectedCategory = event.category)
                    }
                    saveFilter(event.category)
                }
            }

            CategoryUiEvent.OnBackInCategoryDialog -> {
                if (currentState.selectedCategories.isNotEmpty()) {
                    val newList = currentState.selectedCategories.toMutableList()
                    val lastIndex = newList.lastIndex
                    newList.removeAt(lastIndex)
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

    private fun saveFilter(category: Category) {
        viewModelScope.launch {
            saveFilterFromCategoryUseCase.invoke(AdsFilter(category = category))
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