package ghazimoradi.soheil.location

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.input.AppTextField
import ghazimoradi.soheil.divar.ui.core.list.SwipeList
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.CreateSpace
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.location.component.CityItem
import ghazimoradi.soheil.location.component.NeighbourhoodItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun LocationScreen(
    vm: LocationViewModel = hiltViewModel(),
    onMoveToMain: () -> Unit,
    onBack: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.cityIsSelected) {
        if (uiState.cityIsSelected) {
            onMoveToMain()
        }
    }
    LaunchedEffect(key1 = uiState.onBack) {
        if (uiState.onBack) {
            onBack()
        }
    }

    LocationScreenContent(
        modifier = Modifier.baseModifier(padding = 0.dp),
        cities = uiState.cities,
        searchText = uiState.searchText,
        isRefreshing = uiState.isLoading,
        selectedCity = uiState.selectedCity,
        onAction = {
            vm.onTriggerEvent(it)
        }
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreenContent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    cities: ImmutableList<City>?,
    selectedCity: City? = null,
    searchText: String,
    onAction: OnAction
) {
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.itemColor)
                .heightIn(min = 70.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            if (!isSearching) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .animateClickable { isSearching = true },
                    imageVector = Icons.Default.Search,
                    contentDescription = "search on cities",
                    tint = AppTheme.colors.iconColor
                )
                8.CreateSpace()
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .border(1.dp, color = AppTheme.colors.iconColor, shape = CircleShape)
                        .padding(4.dp)
                        .animateClickable { },
                    imageVector = Icons.Default.QuestionMark,
                    contentDescription = "search on cities",
                    tint = AppTheme.colors.iconColor
                )
                BodyMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = stringResource(id = R.string.choose_your_city)
                )
            } else {
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchText,
                    onValueChange = { onAction(LocationUiEvent.OnSearch(it)) },
                    hint = stringResource(id = R.string.search_on_cities),
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
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
                    iconModifier = Modifier
                        .size(24.dp)
                        .animateClickable { isSearching = false }
                )
            }
        }
        selectedCity?.let {
            SwipeList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                isRefreshing = isRefreshing,
                isLoadMore = false,
                listSize = it.neighbourHoods?.size,
                onRefresh = { onAction(LocationUiEvent.OnRefresh) },
                onLoadMore = {}
            ) { index ->
                it.neighbourHoods?.get(index)?.let { neighbourhood ->
                    NeighbourhoodItem(
                        neighbourHood = neighbourhood,
                        onClick = {
                            onAction(
                                LocationUiEvent.OnNeighbourhood(neighbourhood)
                            )
                        }
                    )
                    16.CreateSpace()
                    if (!it.neighbourHoods.isNullOrEmpty() && index != it.neighbourHoods!!.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 0.5.dp
                        )
                    }
                    16.CreateSpace()
                }
            }
        } ?: run {
            SwipeList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                isRefreshing = isRefreshing,
                isLoadMore = false,
                listSize = cities?.size,
                onRefresh = {
                    onAction(LocationUiEvent.OnRefresh)
                },
                onLoadMore = {

                }
            ) { index ->
                cities?.get(index)?.let { city ->
                    CityItem(
                        city = city,
                        onClick = {
                            onAction(
                                LocationUiEvent.OnCity(city)
                            )
                        }
                    )
                    16.CreateSpace()
                    if (index != cities.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 0.5.dp
                        )
                    }
                    16.CreateSpace()
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        LocationScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            cities = FakeData.provideCities().toImmutableList(),
            searchText = "",
            onAction = {}
        )
    }
}
