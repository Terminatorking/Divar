package ghazimoradi.soheil.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.location.LocationScreenType
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.usecases.location.GetCitiesUseCase
import ghazimoradi.soheil.divar.domain.usecases.location.SaveCityUseCase
import ghazimoradi.soheil.divar.domain.usecases.location.SaveNeighbourhoodUseCase
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val saveNeighbourhoodUseCase: SaveNeighbourhoodUseCase
) : BaseViewModel<LocationUiState, LocationUiEvent>() {

    private var originalCities: MutableList<City> = mutableListOf()

    init {
        getLocationScreenType()
        when (currentState.locationScreenType) {
            LocationScreenType.FromLogin -> getCities()
            LocationScreenType.FromCreateAds -> getCitiesWithNeighbourhood()
        }
    }

    private fun getLocationScreenType() {
        savedStateHandle?.get<String>("screenType")?.let {
            val temp = if (it.equals("FromLogin", true)) {
                LocationScreenType.FromLogin
            } else LocationScreenType.FromCreateAds
            setState { copy(locationScreenType = temp) }
        }
    }

    private fun getCities() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getCitiesUseCase.invoke().collect {
                it.onSuccess { cities ->
                    originalCities = cities.toMutableList()
                    setState {
                        copy(
                            isLoading = false,
                            cities = cities.toImmutableList()
                        )
                    }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun getCitiesWithNeighbourhood() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getCitiesUseCase.invoke(true).collect {
                it.onSuccess { cities ->
                    originalCities = cities.toMutableList()
                    setState {
                        copy(
                            isLoading = false,
                            cities = cities.toImmutableList()
                        )
                    }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun saveCity() {
        viewModelScope.launch {
            saveCityUseCase.invoke(currentState.selectedCity!!)
        }
        setState {
            copy(cityIsSelected = true)
        }
    }

    override fun createInitialState() = LocationUiState()

    override fun onTriggerEvent(event: LocationUiEvent) {
        when (event) {
            LocationUiEvent.OnRefresh -> getCities()
            is LocationUiEvent.OnSearch -> {
                setState {
                    copy(
                        searchText = event.text,
                        cities = originalCities.filter { it.name.contains(event.text) }.toImmutableList()
                    )
                }
            }

            is LocationUiEvent.OnCity -> {
                setState { copy(selectedCity = event.city) }
                when (currentState.locationScreenType) {
                    LocationScreenType.FromLogin -> saveCity()
                    LocationScreenType.FromCreateAds -> {}
                }

            }

            is LocationUiEvent.OnNeighbourhood -> {
                setState { copy(selectedNeighbourhood = event.neighbourHood) }
                saveNeighbourhood()
            }
        }
    }

    private fun saveNeighbourhood() {
        viewModelScope.launch {
            saveNeighbourhoodUseCase.invoke(currentState.selectedNeighbourhood!!)
        }
        setState { copy(onBack = true) }
    }
}
