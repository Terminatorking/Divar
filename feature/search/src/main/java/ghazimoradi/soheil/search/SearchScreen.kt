package ghazimoradi.soheil.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.input.AppTextField
import ghazimoradi.soheil.divar.ui.core.list.SwipeList
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.extension.immutableListOf
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.search.component.CategoryOfAdsItem
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchScreen(
    vm: SearchViewModel = hiltViewModel(),
    onSelected: (FromScreen) -> Unit,
    onBack: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.selectedCategoryOfAds) {
        if (uiState.selectedCategoryOfAds != null) {
            onSelected(uiState.fromScreen)
        }
    }

    SearchScreenContent(
        Modifier.baseModifier(0.dp),
        searchText = uiState.adsFilter?.searchText ?: "",
        onAction = { vm.onTriggerEvent(it) },
        list = uiState.categoriesOfAds,
        isLoading = uiState.isLoading,
        onBack = onBack
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onAction: OnAction = {},
    list: ImmutableList<CategoryOfAds> = immutableListOf<CategoryOfAds>(),
    isLoading: Boolean = false,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Box(modifier = Modifier.fillMaxWidth())
        {

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = {
                    onAction(SearchUiEvent.OnChangeText(it))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = AppTheme.colors.primaryColor,
                    unfocusedTextColor = AppTheme.colors.hintColor,
                    disabledTextColor = AppTheme.colors.hintColor,
                    focusedBorderColor = AppTheme.colors.itemColor,
                    unfocusedBorderColor = AppTheme.colors.itemColor,
                    disabledBorderColor = AppTheme.colors.itemColor,
                    focusedContainerColor = AppTheme.colors.itemColor,
                    unfocusedContainerColor = AppTheme.colors.itemColor,
                    disabledContainerColor = AppTheme.colors.itemColor,
                ),
                hint = stringResource(id = R.string.search),
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                maxLine = 1,
                iconModifier = Modifier
                    .size(24.dp)
                    .animateClickable { onBack() }
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .background(AppTheme.colors.titleColor, shape = CircleShape)
                    .padding(4.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "close icon"
            )
        }

        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isRefreshing = isLoading,
            isLoadMore = false,
            listSize = list.size,
            onRefresh = { onAction(SearchUiEvent.OnRefresh) },
            onLoadMore = {}
        ) {
            CategoryOfAdsItem(categoryOfAds = list[it]) {
                onAction(SearchUiEvent.OnSelect(list[it]))
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                thickness = 0.5.dp
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SearchScreenContent(
        modifier = Modifier.baseModifier(), onBack = {}
    )
}