package ghazimoradi.soheil.divar.home

import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUiState, HomeUiEvent>() {
    override fun createInitialState(): HomeUiState {
        TODO("Not yet implemented")
    }

    override fun onTriggerEvent(event: HomeUiEvent) {
        TODO("Not yet implemented")
    }
}