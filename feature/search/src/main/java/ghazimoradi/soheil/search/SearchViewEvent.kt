package ghazimoradi.soheil.search

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class SearchUiState(
    val isLoading: Boolean = false,
    val categoriesOfAds: ImmutableList<CategoryOfAds> = immutableListOf(),
    val selectedCategoryOfAds: CategoryOfAds? = null,
    val fromScreen: FromScreen = FromScreen.Home,
    val adsFilter: AdsFilter? = null
) : UiState

sealed class SearchUiEvent : UiEvent {
    data object OnRefresh : SearchUiEvent()
    data class OnChangeText(val searchText: String) : SearchUiEvent()
    data class OnSelect(val categoryOfAds: CategoryOfAds) : SearchUiEvent()
}

typealias OnAction = (SearchUiEvent) -> Unit