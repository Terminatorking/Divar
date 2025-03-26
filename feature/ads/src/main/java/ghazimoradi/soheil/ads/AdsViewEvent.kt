package ghazimoradi.soheil.ads

import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.filter.FilterClickType
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class AdsUiState(
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val ads: Paging<ImmutableList<AdsSummary>>? = Paging(content = immutableListOf()),
    val page: Int = 0,
    val userCity: City? = null,
    val adsFilter: AdsFilter? = null,
    val navigateToFilter: FilterClickType? = null,
    val navigateToNeighborhood: Boolean = false,
    val showCategoryDialog: Boolean = false,
    val categories: ImmutableList<Category> = immutableListOf(),
    val fromScreen: FromScreen = FromScreen.Home,
) : UiState


sealed class AdsUiEvent : UiEvent {
    data object OnRefresh : AdsUiEvent()
    data object OnLoadMore : AdsUiEvent()
    data object OnDismissDialog : AdsUiEvent()
    data object OnNavigated : AdsUiEvent()
    data class OnFilterClickType(val filterClickType: FilterClickType) : AdsUiEvent()
}

typealias OnAction = (AdsUiEvent) -> Unit
