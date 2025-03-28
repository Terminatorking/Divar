package ghazimoradi.soheil.ads

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.filter.FilterClickType
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.paginate.addMore
import ghazimoradi.soheil.divar.domain.model.parameter.DataType
import ghazimoradi.soheil.divar.domain.usecases.ads.GetAdsSummaryUseCase
import ghazimoradi.soheil.divar.domain.usecases.category.GetCategoriesUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.ReadFilterFromCategoryUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.ReadFilterFromHomeUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.SaveFilterFromCategoryUseCase
import ghazimoradi.soheil.divar.domain.usecases.filter.SaveFilterFromHomeUseCase
import ghazimoradi.soheil.divar.domain.usecases.location.GetUserCityUseCase
import ghazimoradi.soheil.divar.domain.usecases.parameter.GetParametersUseCase
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import ghazimoradi.soheil.divar.utils.fromJson
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsSummaryUseCase: GetAdsSummaryUseCase,
    private val getUserCityUseCase: GetUserCityUseCase,
    private val getParameterUseCase: GetParametersUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val readFilterFromCategoryUseCase: ReadFilterFromCategoryUseCase,
    private val readFilterFromHomeUseCase: ReadFilterFromHomeUseCase,
    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase,
    private val saveFilterFromHomeUseCase: SaveFilterFromHomeUseCase
) : BaseViewModel<AdsUiState, AdsUiEvent>() {
    init {
        getInitData()
        getUserCity()
        if (currentState.adsFilter?.parameters.isNullOrEmpty())
            getParameters()
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        currentState.copy(
                            categories = it.toImmutableList(),
                        )
                    }
                }.onFailure { apiError ->
                    setUiMessage(
                        UIMessage(stringValue = apiError.message)
                    )
                }
            }
        }
    }

    private fun getParameters() {
        currentState.adsFilter?.category?.id?.let { categoryId ->
            viewModelScope.launch {
                getParameterUseCase.invoke(categoryId).collect {
                    it.onSuccess {
                        setState {
                            copy(
                                adsFilter = currentState.adsFilter?.copy(
                                    parameters = it.toImmutableList()
                                )
                            )
                        }
                    }.onFailure { apiError ->
                        setState {
                            copy(isLoading = false)
                        }
                        setUiMessage(
                            UIMessage(stringValue = apiError.message)
                        )
                    }
                }
            }
        }
    }

    private fun getInitData() {
        savedStateHandle?.get<String>("fromScreen")?.let { json ->
            setState {
                copy(fromScreen = json.fromJson<FromScreen>()!!)
            }
            getAdsFilter()
        }
    }

    private fun getAdsFilter() {
        viewModelScope.launch {
            when (currentState.fromScreen) {
                FromScreen.Home -> {
                    readFilterFromHomeUseCase.invoke().collect {
                        setState {
                            copy(adsFilter = it)
                        }
                        if (currentState.userCity != null) getAds()
                    }
                }

                FromScreen.Category -> {
                    readFilterFromCategoryUseCase.invoke().collect {
                        setState {
                            copy(adsFilter = it)
                        }
                        if (currentState.userCity != null) getAds()
                    }
                }
            }
        }
    }

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        copy(userCity = it)
                    }
                    if (currentState.adsFilter != null) getAds()
                }.onFailure { apiError ->
                    setState {
                        copy(isLoading = false)
                    }
                    setUiMessage(
                        UIMessage(stringValue = apiError.message)
                    )
                }
            }
        }
    }

    override fun createInitialState() = AdsUiState()

    override fun onTriggerEvent(event: AdsUiEvent) {
        when (event) {
            AdsUiEvent.OnLoadMore -> {
                if (currentState.ads?.isLast == false) {
                    setState {
                        copy(page = currentState.page + 1)
                    }
                    getAds()
                }
            }

            AdsUiEvent.OnRefresh -> {
                setState {
                    copy(page = 0)
                }
                getAds()
            }

            is AdsUiEvent.OnFilterClickType -> {
                viewModelScope.launch {
                    when (event.filterClickType) {
                        FilterClickType.OnFilter -> {
                            setState {
                                copy(navigateToFilter = event.filterClickType)
                            }
                        }

                        is FilterClickType.OnCategory -> {
                            if (event.filterClickType.isRemove) {
                                setState {
                                    copy(adsFilter = currentState.adsFilter?.copy(category = null))
                                }
                                getAds()
                            } else {
                                setState {
                                    copy(showCategoryDialog = true)
                                }
                            }
                        }

                        is FilterClickType.OnNeighbourhood -> {
                            if (event.filterClickType.isRemove) {
                                setState {

                                    copy(
                                        adsFilter = currentState.adsFilter?.copy(
                                            neighbourHood = null
                                        )
                                    )
                                }
                            } else {
                                setState {
                                    copy(navigateToNeighbourhood = true)
                                }
                                delay(1200)
                                setState {
                                    copy(navigateToNeighbourhood = false)
                                }
                            }
                        }

                        is FilterClickType.OnParameter -> {
                            if (event.filterClickType.isRemove) {
                                setState {
                                    copy(
                                        adsFilter = currentState.adsFilter?.copy(
                                            parameters = adsFilter?.parameters?.map {
                                                if (event.filterClickType.parameter.id == it.id)
                                                    it.copy(answer = null)
                                                else it
                                            }?.toImmutableList()
                                        )
                                    )
                                }
                            } else if (
                                event.filterClickType.parameter.dataType == DataType.CheckBoxInput
                            ) {
                                setState {
                                    copy(
                                        adsFilter = currentState.adsFilter?.copy(
                                            parameters = adsFilter?.parameters?.map {
                                                if (it.id == event.filterClickType.parameter.id)
                                                    it.copy(answer = it.name)
                                                else it
                                            }?.toImmutableList()
                                        )
                                    )
                                }
                                getAds()
                            } else {
                                setState {
                                    copy(navigateToFilter = event.filterClickType)
                                }
                            }
                        }

                        is FilterClickType.OnPrice -> {
                            if (event.filterClickType.isRemove) {
                                setState {
                                    copy(adsFilter = currentState.adsFilter?.copy(price = null))
                                }
                            } else {
                                setState {
                                    copy(navigateToFilter = event.filterClickType)
                                }
                            }
                        }

                        is FilterClickType.OnCategoryToShowAds -> {
                            setState {

                                copy(
                                    showCategoryDialog = false,
                                    adsFilter = adsFilter?.copy(category = event.filterClickType.category)
                                )
                            }
                            getAds()
                            getParameters()
                        }
                    }
                    saveFilter()
                }
            }

            AdsUiEvent.OnDismissDialog -> {
                setState {
                    copy(showCategoryDialog = false)
                }
            }

            AdsUiEvent.OnNavigated -> {
                setState {
                    copy(navigateToFilter = null)
                }
            }
        }
    }

    private suspend fun saveFilter() {
        when (currentState.fromScreen) {
            FromScreen.Home -> saveFilterFromHomeUseCase.invoke(
                currentState.adsFilter?.copy(focus = currentState.navigateToFilter)
            )

            FromScreen.Category -> saveFilterFromCategoryUseCase.invoke(
                currentState.adsFilter?.copy(
                    focus = currentState.navigateToFilter
                )
            )
        }
    }

    private fun getAds() {
        viewModelScope.launch {
            getAdsSummaryUseCase.invoke(
                adsFilter = currentState.adsFilter!!,
                page = currentState.page,
                cityId = currentState.userCity!!.id,
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
                    setUiMessage(
                        UIMessage(stringValue = apiError.message)
                    )
                }
            }
        }
    }
}