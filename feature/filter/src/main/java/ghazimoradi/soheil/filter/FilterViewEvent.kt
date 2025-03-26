package ghazimoradi.soheil.filter

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.filter.FilterClickType
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class FilterUiState(
    val isLoading: Boolean = true,
    val adsFilter: AdsFilter? = null,
    val filterClickType: FilterClickType? = null,
    val minPrice: String = "",
    val maxPrice: String = "",

    val showCategoryDialog: Boolean = false,
    val showParameterDialog: Parameter? = null,

    val allCategories: ImmutableList<Category> = immutableListOf(),

    val fromScreen: FromScreen = FromScreen.Home
) : UiState

sealed class FilterUiEvent : UiEvent {
    data class OnFilterClickType(val filterClickType: FilterClickType) : FilterUiEvent()
    data class OnMaxPriceChange(val value: String) : FilterUiEvent()
    data class OnMinPriceChange(val value: String) : FilterUiEvent()
    // when user select an option in parameter dialog
    data class OnAnswerToParameter(val parameter: Parameter) : FilterUiEvent()
    data object DismissDialog : FilterUiEvent()
    data object OnClearFilter : FilterUiEvent()
    data object OnSaveFilter : FilterUiEvent()
}

typealias OnAction = (FilterUiEvent) -> Unit
