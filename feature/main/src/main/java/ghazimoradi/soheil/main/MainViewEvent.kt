package ghazimoradi.soheil.main

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState

@Stable
data class MainUiState(
    val isLoading: Boolean = true,
    val selectedIndex: Int = 4,
) : UiState

sealed class MainUiEvent : UiEvent {
    data class OnChangeTab(val index: Int) : MainUiEvent()
}

typealias OnAction = (MainUiEvent) -> Unit
