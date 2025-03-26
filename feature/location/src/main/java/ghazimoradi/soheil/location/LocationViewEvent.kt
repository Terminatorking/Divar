package ghazimoradi.soheil.location

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.location.LocationScreenType
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import kotlinx.collections.immutable.ImmutableList

@Stable
data class LocationUiState(
    val isLoading: Boolean = true,
    val searchText: String = "",
    val cities: ImmutableList<City>? = null,
    val selectedCity: City? = null,
    val cityIsSelected: Boolean = false,
    val locationScreenType: LocationScreenType = LocationScreenType.FromLogin,
    val selectedNeighbourhood: NeighbourHood? = null,
    val onBack: Boolean = false,
) : UiState

sealed class LocationUiEvent : UiEvent {
    data object OnRefresh : LocationUiEvent()
    data class OnSearch(val text: String) : LocationUiEvent()
    data class OnCity(val city: City) : LocationUiEvent()
    data class OnNeighbourhood(val neighbourHood: NeighbourHood) : LocationUiEvent()
}

typealias OnAction = (LocationUiEvent) -> Unit