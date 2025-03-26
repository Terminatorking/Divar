package ghazimoradi.soheil.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.usecases.user.IsLoginUseCase
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val isLoginUseCase: IsLoginUseCase
) : BaseViewModel<MainUiState, MainUiEvent>() {

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            isLoginUseCase().collect {
                setState {
                    copy(isUserLoggedIn = it)
                }
            }
        }
    }

    override fun createInitialState() = MainUiState()

    override fun onTriggerEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OnChangeTab -> {
                setState {
                    copy(selectedIndex = event.index)
                }
            }
        }
    }
}