package ghazimoradi.soheil.divar.home
import ghazimoradi.soheil.divar.ui.viewmodel.UiState
import androidx.compose.runtime.Stable
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.ui.viewmodel.UiEvent
import kotlinx.collections.immutable.ImmutableList
import ghazimoradi.soheil.divar.ui.extension.immutableListOf

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val categories: ImmutableList<Category>? = immutableListOf(),
    val showCategories: ImmutableList<Category>? = immutableListOf(),
    val selectedCategories: ImmutableList<Category>? = immutableListOf(),
    val searchedCategories: ImmutableList<Category>? = immutableListOf(),
    val ads: Paging<ImmutableList<AdsSummary>>? = Paging(content = immutableListOf()),
    val page: Int = 0,
    val emptyCategoryCount: Int = 0,
    val categorySearchText: String = "",
    val userCity: City? = null,
    val selectedCategory: Category? = null
) : UiState

sealed class HomeUiEvent : UiEvent {
    data object OnRefreshing : HomeUiEvent()
    data object OnLoadMore : HomeUiEvent()
    data object OnClearCategory : HomeUiEvent()
    data object OnRemoveSelectedCategory : HomeUiEvent()
    data class OnSelectedCategory(val category: Category) : HomeUiEvent()
    data class OnCategorySearchChange(val text: String) : HomeUiEvent()
    data object OnClearSelectedCategory : HomeUiEvent()
}

typealias OnAction = (HomeUiEvent) -> Unit