package ghazimoradi.soheil.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.usecases.user.IsLoginUseCase
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val isLoginUseCase: IsLoginUseCase,
) : BaseViewModel<SplashUiState, SplashUiEvent>() {

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            isLoginUseCase().collect {
                setState {
                    copy(userIsLogin = it)
                }
            }
        }
    }
    override fun createInitialState() = SplashUiState()

    override fun onTriggerEvent(event: SplashUiEvent) {}
}
