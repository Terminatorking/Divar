package ghazimoradi.soheil.create_ads

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.parameter.DataType
import ghazimoradi.soheil.divar.domain.usecases.ads.CreateAdsUseCase
import ghazimoradi.soheil.divar.domain.usecases.category.GetCategoriesUseCase
import ghazimoradi.soheil.divar.domain.usecases.location.GetNeighbourhoodUseCase
import ghazimoradi.soheil.divar.domain.usecases.parameter.GetParametersUseCase
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import ghazimoradi.soheil.divar.utils.findIndex
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.model.MessageStatus
import ghazimoradi.soheil.divar.ui.model.MessageType

@HiltViewModel
class CreateAdsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getParametersUseCase: GetParametersUseCase,
    private val createAdsUseCase: CreateAdsUseCase,
    private val getNeighbourhoodUseCase: GetNeighbourhoodUseCase
) : BaseViewModel<CreateAdsUiState, CreateAdsUiEvent>() {
    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess { categories ->
                    setState { currentState.copy(allCategories = categories.toImmutableList()) }
                }.onFailure { apiError ->
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    override fun createInitialState() = CreateAdsUiState()

    private fun step2Validate(): Boolean {
        return true
    }

    override fun onTriggerEvent(event: CreateAdsUiEvent) {
        when (event) {
            CreateAdsUiEvent.DismissDialog -> {
                setState {
                    copy(
                        showCategoryDialog = false,
                        imageIndexChooser = null,
                        showParameterDialog = null
                    )
                }
            }

            CreateAdsUiEvent.OnNext -> {
                when (currentState.screenStep) {
                    ScreenStep.Step1 -> {
                        if (step1Validate()) setState { copy(screenStep = ScreenStep.Step2) }
                    }

                    ScreenStep.Step2 -> {
                        if (step2Validate()) {
                            createAds()
                        }
                    }
                }
            }

            is CreateAdsUiEvent.OnSelectCategory -> {
                setState {
                    copy(
                        createAdsParam = createAdsParam.copy(category = event.category),
                        showCategoryDialog = false
                    )
                }
                getParameter()
            }

            CreateAdsUiEvent.ShowCategoryDialog -> {
                setState { copy(showCategoryDialog = true) }
            }

            is CreateAdsUiEvent.OnImageChooser -> {
                setState { copy(imageIndexChooser = event.index) }
            }

            is CreateAdsUiEvent.OmImagePicked -> {
                if (event.pathList.size == 1) {
                    setState {
                        copy(
                            createAdsParam = createAdsParam.copy(images = currentState.createAdsParam.images.mapIndexed { index, s ->
                                if (index == currentState.imageIndexChooser) event.pathList.first()
                                else s
                            }.toImmutableList())
                        )
                    }
                } else {
                    val temp = currentState.createAdsParam.images.toMutableList()
                    val filledIndexes: MutableList<Int> = mutableListOf()
                    event.pathList.forEachIndexed { _, path ->
                        temp.findIndex { it.isEmpty() }?.let {
                            filledIndexes.add(it)
                            temp[it] = path
                        } ?: run {
                            temp.forEachIndexed { index, s ->
                                if (index !in filledIndexes) {
                                    temp[index] = path
                                }
                            }
                        }
                    }
                    setState { copy(createAdsParam = createAdsParam.copy(images = temp.toImmutableList())) }
                }
            }

            is CreateAdsUiEvent.OnTitleChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(title = event.text)) }
            }

            is CreateAdsUiEvent.OnDescriptionChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(description = event.text)) }
            }

            is CreateAdsUiEvent.OnNeighbourhood -> {
                viewModelScope.launch {
                    setState { copy(toNeighbourhood = true) }
                    delay(2000)
                    setState { copy(toNeighbourhood = false) }
                }
            }

            is CreateAdsUiEvent.OnPriceChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(price = event.text)) }
            }

            is CreateAdsUiEvent.OnParameter -> {
                when (event.parameter.dataType) {
                    DataType.CheckBoxInput -> {
                        setState {
                            copy(
                                parameters = parameters.map {
                                    if (it.id == event.parameter.id)
                                        it.copy(answer = it.name)
                                    else it
                                }.toImmutableList()
                            )
                        }
                    }

                    DataType.FixedOption -> {
                        setState { copy(showParameterDialog = event.parameter) }
                    }

                    else -> {
                        setState {
                            copy(
                                parameters = parameters.map {
                                    if (it.id == event.parameter.id)
                                        event.parameter
                                    else it
                                }.toImmutableList()
                            )
                        }
                    }
                }
            }

            is CreateAdsUiEvent.OnAnswerToParameter -> {
                setState {
                    copy(
                        showParameterDialog = null,
                        parameters = parameters.map {
                            if (it.id == event.parameter.id) event.parameter
                            else it
                        }.toImmutableList()
                    )
                }
            }

            CreateAdsUiEvent.CheckNeighbourhood -> {
                getNeighbourhood()
            }
        }
    }

    private fun getNeighbourhood() {
        viewModelScope.launch {
            getNeighbourhoodUseCase.invoke().collect {
                it.onSuccess {
                    setState { copy(createAdsParam = createAdsParam.copy(neighbourHood = it)) }
                }.onFailure { _ ->
                }
            }
        }
    }

    private fun getParameter() {
        viewModelScope.launch {
            getParametersUseCase.invoke(currentState.createAdsParam.category!!.id).collect {
                it.onSuccess {
                    setState { copy(parameters = it.toImmutableList()) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun step1Validate(): Boolean {
        currentState.createAdsParam.let {
            if (it.category == null) {
                setUiMessage(UIMessage(intValue = R.string.error_create_ads_select_category))
                return false
            } else if (it.title.isEmpty()) {
                setUiMessage(UIMessage(intValue = R.string.error_create_ads_title))
                return false
            } else if (it.description.isEmpty()) {
                setUiMessage(UIMessage(intValue = R.string.error_create_ads_description))
                return false
            }
        }
        return true
    }


    private fun createAds() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            createAdsUseCase.invoke(currentState.createAdsParam.copy(parameters = currentState.parameters))
                .collect {
                    setState { copy(isLoading = false) }
                    it.onSuccess {
                        setUiMessage(
                            UIMessage(
                                intValue = R.string.ads_created_successful,
                                messageType = MessageType.System,
                                status = MessageStatus.Success
                            )
                        )
                        delay(2 * 1000)
                        setState { copy(adsCreated = true, isLoading = false) }
                    }.onFailure { apiError ->
                        setUiMessage(UIMessage(stringValue = apiError.message))
                    }
                }
        }
    }
}
