package ghazimoradi.soheil.divar.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghazimoradi.soheil.divar.ui.model.UIMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent> : ViewModel() {

    private val initialState: State by lazy {
        createInitialState()
    }

    @Stable
    val currentState: State get() = uiState.value

    @Stable
    abstract fun createInitialState(): State

    @Stable
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)

    @Stable
    val uiState: StateFlow<State> = _uiState

    @Stable
    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()

    @Stable
    val uiEvent: SharedFlow<Event> = _uiEvent

    @Stable
    private val _uiMessage: MutableSharedFlow<UIMessage?> = MutableSharedFlow()

    @Stable
    val uiMessage: SharedFlow<UIMessage?> = _uiMessage

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setUiMessage(uiMessage: UIMessage, delay: Long = 3000L) {
        viewModelScope.launch {
            _uiMessage.emit(uiMessage)
            delay(timeMillis = delay)
            _uiMessage.emit(null)
        }
    }

    @Stable
    abstract fun onTriggerEvent(event: Event)

}