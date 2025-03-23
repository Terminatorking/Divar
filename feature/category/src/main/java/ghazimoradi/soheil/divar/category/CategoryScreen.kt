package ghazimoradi.soheil.divar.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.category.component.CategoryItem
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.list.SwipeList
import ghazimoradi.soheil.divar.ui.core.texts.TitleMediumText
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.CreateSpace
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onCategory: (Category) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(key1 = uiState.selectedCategory) {
        if (uiState.selectedCategory != null) {
            onCategory(uiState.selectedCategory)
            viewModel.onTriggerEvent(CategoryUiEvent.OnClearSelectedCategory)
        }
    }
    CategoryScreenContent(
        categoryTitle = uiState.categoryTitle,
        list = uiState.showCategories,
        isRefreshing = uiState.isRefreshing,
        isLoadMore = uiState.isLoadMore,
        onAction = { categoryUiEvent ->
            viewModel.onTriggerEvent(categoryUiEvent)
        }
    )

    UiMessageScreen(shared = viewModel.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenContent(
    categoryTitle: String? = null,
    list: ImmutableList<Category>?,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    onAction: OnAction,
) {
    AppTheme {
        Scaffold(
            modifier = Modifier.baseModifier(padding = 0.dp),
        ) { innerPadding ->
            innerPadding.eLog()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.backgroundColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(1.dp)
                        .background(AppTheme.colors.itemColor)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                ) {
                    TitleMediumText(
                        text = if (categoryTitle.isNullOrEmpty())
                            stringResource(R.string.ads_category) else categoryTitle
                    )

                    if (categoryTitle.isNullOrEmpty().not()) {
                        8.CreateSpace()
                        Icon(
                            modifier = Modifier
                                .animateClickable {
                                    onAction(CategoryUiEvent.OnBackInCategoryDialog)
                                }
                                .size(18.dp),
                            tint = AppTheme.colors.iconColor,
                            painter = painterResource(R.drawable.ic_arrow_right),
                            contentDescription = "back"
                        )
                    }
                }
                SwipeList(
                    listSize = list?.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    isRefreshing = isRefreshing,
                    isLoadMore = isLoadMore,
                    onRefresh = {
                        onAction.invoke(CategoryUiEvent.OnRefresh)
                    },
                    onLoadMore = {
                        onAction.invoke(CategoryUiEvent.OnLoadMore)
                    },
                ) { index ->
                    list?.get(index)?.apply {
                        CategoryItem(
                            onClick = {
                                onAction.invoke(
                                    CategoryUiEvent.OnCategorySelected(category = this)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            category = this,
                        )
                        12.CreateSpace()
                        HorizontalDivider()
                        12.CreateSpace()
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryScreenContent(
            list = FakeData.provideCategories(),
            isRefreshing = false,
            isLoadMore = false,
        ) {

        }
    }
}