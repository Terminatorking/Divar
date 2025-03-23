package ghazimoradi.soheil.divar.home

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiSate

@Stable
data class HomeUiState(val isLoading: Boolean = true) : UiSate

sealed class HomeUiEvent : UiEvent {

}