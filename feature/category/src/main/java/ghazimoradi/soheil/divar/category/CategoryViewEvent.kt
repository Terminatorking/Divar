package ghazimoradi.soheil.divar.category

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiSate
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CategoryUiState(
    val isLoading: Boolean = true,
    val categories: ImmutableList<Category> = immutableListOf(),
    val selectedCategories: ImmutableList<Category> = immutableListOf(),
) : UiSate

sealed class CategoryUiEvent : UiEvent {
    data class OnCategorySelected(val category: Category) : CategoryUiEvent()
}