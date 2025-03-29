package ghazimoradi.soheil.splash

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState

@Stable
data class SplashUiState(
    val userIsLogin: Boolean = false,
) : UiState

sealed class SplashUiEvent : UiEvent
