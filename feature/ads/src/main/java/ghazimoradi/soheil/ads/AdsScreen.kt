package ghazimoradi.soheil.ads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.ads.component.AdsToolbar
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.filter.FilterClickType
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.ui.category.CategoryDialog
import ghazimoradi.soheil.divar.ui.core.ads.AdsItem
import ghazimoradi.soheil.divar.ui.core.list.SwipeList
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun AdsScreen(
    vm: AdsViewModel = hiltViewModel(),
    onCity: () -> Unit,
    onBack: () -> Unit,
    onSearch: (FromScreen) -> Unit,
    onFilter: (FromScreen) -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.navigateToNeighbourhood) {
        if (uiState.navigateToNeighbourhood) {

        }
    }

    LaunchedEffect(key1 = uiState.navigateToFilter) {
        if (uiState.navigateToFilter != null && uiState.adsFilter != null) {
            onFilter(uiState.fromScreen)
            vm.onTriggerEvent(AdsUiEvent.OnNavigated)
        }
    }

    AdsScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        cityName = uiState.userCity?.name ?: "",
        onSearch = { onSearch(uiState.fromScreen) },
        onCity = onCity,
        onBack = onBack,
        onAction = { vm.onTriggerEvent(it) },
        isLoading = uiState.isLoading,
        isLoadMore = uiState.isLoadMore,
        searchText = uiState.adsFilter?.searchText ?: "",
        list = uiState.ads,
        adsFilter = uiState.adsFilter
    )

    if (uiState.showCategoryDialog) {
        CategoryDialog(
            modifier = Modifier,
            categories = uiState.categories,
            onDismiss = { vm.onTriggerEvent(AdsUiEvent.OnDismissDialog) },
            onShowAds = {
                vm.onTriggerEvent(
                    AdsUiEvent.OnFilterClickType(
                        FilterClickType.OnCategoryToShowAds(it)
                    )
                )
            }
        )
    }

    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdsScreenContent(
    modifier: Modifier = Modifier,
    cityName: String,
    isLoading: Boolean,
    isLoadMore: Boolean,
    onAction: OnAction,
    onCity: () -> Unit,
    onSearch: () -> Unit,
    onBack: () -> Unit,
    searchText: String = "",
    list: Paging<ImmutableList<AdsSummary>>? = null,
    adsFilter: AdsFilter? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        AdsToolbar(
            cityName = cityName,
            onCity = onCity,
            onSearch = onSearch,
            searchText = searchText,
            adsFilter = adsFilter,
            onBack = onBack,
            onAction = onAction
        )
        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isRefreshing = isLoading,
            isLoadMore = isLoadMore,
            listSize = list?.content?.size,
            onRefresh = { onAction(AdsUiEvent.OnRefresh) },
            onLoadMore = { onAction(AdsUiEvent.OnLoadMore) }
        ) { index ->
            list?.content?.getOrNull(index)?.let { adsSummary ->
                AdsItem(adsSummary = adsSummary, onClick = {})
                if (index != list.content.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        AdsScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            cityName = "مشهد",
            isLoading = false,
            isLoadMore = false,
            onAction = {},
            onCity = {},
            onSearch = {},
            list = null,
            onBack = {}
        )
    }
}