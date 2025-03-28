package ghazimoradi.soheil.divar.category

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CategoryUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = true,
    val isLoadMore: Boolean = false,
    val categories: ImmutableList<Category>? = immutableListOf(),
    val selectedCategories: ImmutableList<Category> = immutableListOf(),
    val showCategories: ImmutableList<Category>? = immutableListOf(),
    val categoryTitle: String? = null,
    val selectedCategory: Category? = null
) : UiState

sealed class CategoryUiEvent : UiEvent {
    data class OnCategorySelected(val category: Category) : CategoryUiEvent()
    data object OnRefresh : CategoryUiEvent()
    data object OnLoadMore : CategoryUiEvent()
    data object OnBackInCategoryDialog : CategoryUiEvent()
    data object OnClearSelectedCategory : CategoryUiEvent()
}

typealias OnAction = (CategoryUiEvent) -> Unit