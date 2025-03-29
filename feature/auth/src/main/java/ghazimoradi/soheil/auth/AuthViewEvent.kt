package ghazimoradi.soheil.auth

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.user.User
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState

@Stable
data class AuthUiState(
    val isLoading: Boolean = false,
    val screenMode: ScreenMode = ScreenMode.Login,
    val mobile: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val user: User? = null,
    val userIsSelectedCity: Boolean? = null,
) : UiState

sealed class AuthUiEvent : UiEvent {
    data class OnTextChanged(val typingType: TypingType) : AuthUiEvent()
    data class OnChangeMode(val screenMode: ScreenMode) : AuthUiEvent()
    data object OnBtnClick : AuthUiEvent()
}

typealias OnAction = (AuthUiEvent) -> Unit

enum class ScreenMode { Login, Register }

sealed class TypingType {
    data class Mobile(val text: String) : TypingType()
    data class Password(val text: String) : TypingType()
    data class RepeatPassword(val text: String) : TypingType()
}