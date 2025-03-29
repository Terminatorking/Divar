package ghazimoradi.soheil.main

import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainUiState, MainUiEvent>() {

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