package ghazimoradi.soheil.divar.category

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?
) : BaseViewModel<CategoryUiState, CategoryUiEvent>() {

    override fun createInitialState() = CategoryUiState()

    override fun onTriggerEvent(event: CategoryUiEvent) {

    }
}