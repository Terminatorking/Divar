package ghazimoradi.soheil.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.usecases.location.GetUserCityUseCase
import ghazimoradi.soheil.divar.domain.usecases.user.LoginUseCase
import ghazimoradi.soheil.divar.domain.usecases.user.RegisterUseCase
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.model.UIMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserCityUseCase: GetUserCityUseCase,
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<AuthUiState, AuthUiEvent>() {
    init {
        isUserSelectedCity()
    }

    private fun isUserSelectedCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        copy(userIsSelectedCity = true)
                    }
                }.onFailure { apiError ->
                    setState {
                        copy(userIsSelectedCity = false)
                    }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    override fun createInitialState() = AuthUiState()

    override fun onTriggerEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.OnChangeMode -> {
                setState { copy(screenMode = event.screenMode) }
            }

            is AuthUiEvent.OnTextChanged -> {
                when (event.typingType) {
                    is TypingType.Mobile -> setState { copy(mobile = event.typingType.text) }
                    is TypingType.Password -> setState { copy(password = event.typingType.text) }
                    is TypingType.RepeatPassword -> setState { copy(repeatPassword = event.typingType.text) }
                }
            }

            AuthUiEvent.OnBtnClick -> {
                if (!validate()) return
                when (currentState.screenMode) {
                    ScreenMode.Login -> login()
                    ScreenMode.Register -> register()
                }

            }
        }
    }

    private fun validate(): Boolean {
        return when {
            currentState.mobile.isEmpty() -> {
                setUiMessage(UIMessage(intValue = R.string.enter_mobile))
                false
            }

            currentState.password.isEmpty() -> {
                setUiMessage(UIMessage(intValue = R.string.enter_password))
                false
            }

            currentState.screenMode == ScreenMode.Register && currentState.repeatPassword.isEmpty() -> {
                setUiMessage(UIMessage(intValue = R.string.enter_repeat_password))
                false
            }

            currentState.screenMode == ScreenMode.Register && currentState.password != currentState.repeatPassword -> {
                setUiMessage(UIMessage(intValue = R.string.password_repeat_not_same))
                false
            }

            else -> true
        }
    }

    private fun login() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            loginUseCase.invoke(currentState.mobile, currentState.password).collect {
                it.onSuccess {
                    setState { copy(isLoading = false) }
                    setState { copy(user = it) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun register() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            registerUseCase.invoke(
                currentState.mobile,
                currentState.password,
                currentState.repeatPassword
            ).collect {
                it.onSuccess {
                    setState { copy(user = it, isLoading = false) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UIMessage(stringValue = apiError.message))
                }
            }
        }
    }
}

