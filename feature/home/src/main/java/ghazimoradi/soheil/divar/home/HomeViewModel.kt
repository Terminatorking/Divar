package ghazimoradi.soheil.divar.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.paginate.addMore
import ghazimoradi.soheil.divar.domain.usecases.ads.GetAdsSummaryUseCase
import ghazimoradi.soheil.divar.domain.usecases.category.GetCategoriesUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.SaveFilterFromHomeUseCase
import ghazimoradi.soheil.divar.domain.usecases.location.GetUserCityUseCase
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsSummaryUseCase: GetAdsSummaryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getUserCityUseCase: GetUserCityUseCase,
    private val saveFilterFromHomeUseCase: SaveFilterFromHomeUseCase
) : BaseViewModel<HomeUiState, HomeUiEvent>() {

    init {
        getUserCity()
    }

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess { city ->
                    setState {
                        copy(userCity = city)
                    }
                    getCategories()
                    getAds()
                }.onFailure { apiError ->
                    setState {
                        copy(isLoading = false)
                    }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                    apiError.eLog(tag = "serverError")
                }
            }
        }
    }

    override fun createInitialState() = HomeUiState()

    override fun onTriggerEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnLoadMore -> {
                if (currentState.ads?.isLast == false) {
                    setState {
                        copy(page = currentState.page + 1)
                    }
                    getAds()
                }
            }

            HomeUiEvent.OnRefreshing -> {
                setState {
                    copy(page = 0)
                }
                getAds()
                if (currentState.categories.isNullOrEmpty())
                    getCategories()
            }

            is HomeUiEvent.OnSelectedCategory -> {
                if (event.category.children.isEmpty()) {
                    setState {
                        copy(selectedCategory = event.category)
                    }
                    saveFilter(event.category)
                } else {
                    setState {
                        copy(
                            selectedCategories =
                                selectedCategories?.toMutableList()?.apply {
                                    add(event.category)
                                }
                                    ?.toImmutableList()
                                    ?: listOf(event.category).toImmutableList(),
                            showCategories = event.category.children.toImmutableList()
                        )
                    }
                }
            }

            HomeUiEvent.OnRemoveSelectedCategory -> {
                val tempSelected =
                    currentState.selectedCategories?.toMutableList()?.apply {
                        removeLastOrNull()
                    }
                        ?.toImmutableList()
                setState {
                    copy(
                        selectedCategories = tempSelected,
                        showCategories = tempSelected?.lastOrNull()?.children?.toImmutableList()
                    )
                }
            }

            HomeUiEvent.OnClearCategory -> {
                setState {
                    copy(
                        showCategories = immutableListOf(),
                        selectedCategories = immutableListOf()
                    )
                }
            }

            is HomeUiEvent.OnCategorySearchChange -> {
                setState {
                    copy(
                        categorySearchText = event.text,
                        searchedCategories = findCategoriesByName(
                            currentState.categories,
                            event.text
                        )
                    )
                }
            }

            HomeUiEvent.OnClearSelectedCategory -> {
                setState {
                    copy(selectedCategory = null)
                }
            }
        }
    }

    private fun saveFilter(category: Category) {
        viewModelScope.launch {
            saveFilterFromHomeUseCase.invoke(AdsFilter(category = category))
        }
    }

    private fun findCategoriesByName(
        categories: List<Category>?,
        searchText: String
    ): ImmutableList<Category> {
        val temp = mutableListOf<Category>()

        categories?.forEach { category ->
            if (category.name.contains(searchText, ignoreCase = true)) {
                temp.add(category)
            }
            if (category.children.isNotEmpty()) {
                temp.addAll(findCategoriesByName(category.children, searchText))
            }
        }

        return temp.toImmutableList()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess { categories ->
                    var size = categories.size
                    while (size % 4 != 0) {
                        size++
                    }
                    setState {
                        copy(
                            isLoading = false,
                            emptyCategoryCount = size - categories.size,
                            categories = categories.toImmutableList()
                        )
                    }
                }.onFailure { apiError ->
                    setState {
                        copy(isLoading = false)
                    }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                    apiError.eLog("serverError")
                }
            }
        }
    }

    private fun getAds() {
        if (currentState.page > 0) setState {
            copy(isLoadMore = true)
        }
        else setState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            getAdsSummaryUseCase.invoke(
                adsFilter = AdsFilter(),
                cityId = currentState.userCity?.id ?: 0,
                page = currentState.page
            ).collect {
                it.onSuccess { paging ->
                    if (paging.isFirst || currentState.ads?.content.isNullOrEmpty()) {
                        setState {
                            copy(isLoading = false, isLoadMore = false, ads = paging)
                        }
                    } else {
                        setState {
                            copy(
                                isLoading = false,
                                isLoadMore = false,
                                ads = ads?.addMore(
                                    paging = paging,
                                    content = (ads.content + paging.content).toImmutableList()
                                )
                            )
                        }
                    }
                }.onFailure { apiError ->
                    setState {
                        copy(isLoading = false, isLoadMore = false)
                    }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                    apiError.eLog("serverError")
                }
            }
        }
    }
}
