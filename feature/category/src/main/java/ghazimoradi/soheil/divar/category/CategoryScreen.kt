package ghazimoradi.soheil.divar.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CategoryScreen(
    vm: CategoryViewModel = hiltViewModel(),
    onCategory: (Category) -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.selectedCategory) {
        if (uiState.selectedCategory != null) {
            onCategory(uiState.selectedCategory)
            vm.onTriggerEvent(CategoryUiEvent.OnClearSelectedCategory)
        }
    }

    CategoryScreenContent(
        modifier = Modifier
            .baseModifier(0.dp),
        list = uiState.showCategories,
        isRefreshing = uiState.isRefreshing,
        isLoadMore = uiState.isLoadMore,
        categoryTitle = uiState.categoryTitle,
        onAction = { vm.onTriggerEvent(it) }
    )

    UiMessageScreen(
        shared = vm.uiMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenContent(
    modifier: Modifier = Modifier,
    list: ImmutableList<Category>?,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    categoryTitle: String? = null,
    onAction: OnAction
) {
    Column(modifier = modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .shadow(1.dp)
                .background(color = AppTheme.colors.itemColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End)
        ) {
            TitleMediumText(
                text = if (categoryTitle.isNullOrEmpty()) stringResource(id = R.string.ads_category) else categoryTitle
            )

            if (!categoryTitle.isNullOrEmpty()) {
                12.CreateSpace()
                Icon(
                    modifier = Modifier
                        .animateClickable { onAction(CategoryUiEvent.OnBackInCategoryDialog) }
                        .size(18.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "back icon",
                    tint = AppTheme.colors.iconColor
                )
            }
        }
        16.CreateSpace()

        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isRefreshing = isRefreshing,
            isLoadMore = isLoadMore,
            listSize = list?.size,
            onRefresh = { onAction(CategoryUiEvent.OnRefresh) },
            onLoadMore = { onAction(CategoryUiEvent.OnLoadMore) }
        ) { index: Int ->
            list?.get(index)?.apply {
                CategoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    category = this,
                    onClick = {
                        onAction(CategoryUiEvent.OnCategorySelected(this))
                    }
                )
                16.CreateSpace()
                HorizontalDivider()
                16.CreateSpace()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryScreenContent(
            modifier = Modifier.baseModifier(),
            list = FakeData.provideCategories(),
            isRefreshing = false,
            isLoadMore = false,
        ) {

        }
    }
}