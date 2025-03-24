package ghazimoradi.soheil.divar.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.home.component.CategoriesDialog
import ghazimoradi.soheil.divar.home.component.CategoryHomeItem
import ghazimoradi.soheil.divar.home.component.HomeToolbar
import ghazimoradi.soheil.divar.ui.core.ads.AdsItem
import ghazimoradi.soheil.divar.ui.core.failed_screen.FailedScreen
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.CreateSpace
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onCity: () -> Unit = {},
    onSearch: () -> Unit = {},
    onSelectedCategory: (Category) -> Unit,
    onAdsClick: (Long) -> Unit
) {
    val uiState = vm.uiState.collectAsState().value
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = scrollState.canScrollForward) {
        if (!scrollState.canScrollForward
            && !uiState.categories.isNullOrEmpty()
            && !uiState.isLoadMore
        ) {
            vm.onTriggerEvent(HomeUiEvent.OnLoadMore)
        }
    }
    LaunchedEffect(key1 = uiState.selectedCategory) {
        if (uiState.selectedCategory != null) {
            onSelectedCategory(uiState.selectedCategory)
            vm.onTriggerEvent(HomeUiEvent.OnClearSelectedCategory)
        }
    }
    HomeScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        isRefreshing = uiState.isLoading,
        isLoadMore = uiState.isLoadMore,
        ads = uiState.ads,
        categories = uiState.categories,
        scrollState = scrollState,
        emptyCategoryCount = uiState.emptyCategoryCount,
        cityName = uiState.userCity?.name ?: "",
        onCity = onCity,
        onSearch = onSearch,
        onAction = { homeUiEvent ->
            vm.onTriggerEvent(homeUiEvent)
        },
        onAdsClick = { adsSummary ->
            onAdsClick(adsSummary.id)
        }
    )
    if (!uiState.selectedCategories.isNullOrEmpty()) {
        CategoriesDialog(
            modifier = Modifier.fillMaxWidth(),
            showCategories = uiState.showCategories,
            selectedCategories = uiState.selectedCategories,
            searchedCategories = uiState.searchedCategories,
            searchText = uiState.categorySearchText,
            onAction = { homeUiEvent ->
                vm.onTriggerEvent(homeUiEvent)
            },
        )
    }
    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    emptyCategoryCount: Int = 0,
    categories: ImmutableList<Category>?,
    ads: Paging<ImmutableList<AdsSummary>>?,
    scrollState: ScrollState = rememberScrollState(),
    cityName: String = "",
    onAction: OnAction,
    onCity: () -> Unit = {},
    onSearch: () -> Unit = {},
    onAdsClick: (AdsSummary) -> Unit = {},
) {
    val state = rememberPullToRefreshState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {

        HomeToolbar(
            modifier = Modifier,
            cityName = cityName,
            onCity = onCity,
            onSearch = onSearch
        )
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = isRefreshing,
            state = state,
            onRefresh = {
                onAction(HomeUiEvent.OnRefreshing)
            },
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(
                        24.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    categories?.forEachIndexed { index, category ->
                        if (index == categories.size - 1) {
                            repeat(emptyCategoryCount)
                            {
                                60.CreateSpace()
                            }
                        }
                        CategoryHomeItem(
                            modifier = Modifier
                                .width(60.dp)
                                .fillMaxRowHeight(),
                            category = category,
                            onClick = {
                                onAction(HomeUiEvent.OnSelectedCategory(category))
                            }
                        )
                    } ?: run {
                        FailedScreen(onRefresh = { })
                    }
                }
                8.CreateSpace()
                FlowColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    ads?.content?.forEachIndexed { index, adsSummary ->
                        AdsItem(
                            adsSummary = adsSummary,
                            onClick = {
                                onAdsClick(adsSummary)
                            }
                        )
                        if (index != ads.content.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                thickness = 0.5.dp
                            )
                        }
                    } ?: run {
                        FailedScreen(onRefresh = { })
                    }
                }
                if (isLoadMore) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp)
                            .navigationBarsPadding()
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        color = AppTheme.colors.titleColor
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
        HomeScreenContent(
            modifier = Modifier.baseModifier(),
            isRefreshing = false,
            isLoadMore = false,
            categories = FakeData.provideCategories(),
            ads = Paging(FakeData.provideAdsSummary().toImmutableList()),
            onAction = {

            }
        )
    }
}