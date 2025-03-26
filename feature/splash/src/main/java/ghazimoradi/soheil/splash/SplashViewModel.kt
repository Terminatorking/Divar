package ghazimoradi.soheil.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.usecases.location.GetUserCityUseCase
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getUserCityUseCase: GetUserCityUseCase
) : BaseViewModel<SplashUiState, SplashUiEvent>() {

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

    override fun createInitialState() = SplashUiState()

    override fun onTriggerEvent(event: SplashUiEvent) {}
}
