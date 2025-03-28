package ghazimoradi.soheil.ads.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.ads.AdsUiEvent
import ghazimoradi.soheil.ads.OnAction
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.filter.FilterClickType
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.core.texts.LabelSmallText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
internal fun AdsToolbar(
    modifier: Modifier = Modifier,
    searchText: String,
    cityName: String,
    onCity: () -> Unit,
    onSearch: () -> Unit,
    onBack: () -> Unit,
    adsFilter: AdsFilter?,
    onAction: OnAction = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(4.dp)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(
                        1.dp,
                        AppTheme.colors.hintColor,
                        shape = AppTheme.shapes.roundExtraSmall
                    )
                    .padding(4.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "location icon",
                    tint = AppTheme.colors.hintColor
                )

                LabelSmallText(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .animateClickable(onCity),
                    text = cityName
                )

                VerticalDivider(
                    modifier = Modifier
                        .background(AppTheme.colors.hintColor)
                        .padding(start = 0.dp)
                        .height(20.dp)
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search icon",
                    tint = AppTheme.colors.hintColor
                )

                BodyMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .animateClickable(onSearch)
                        .padding(top = 4.dp, end = 4.dp),
                    text = searchText.ifEmpty { stringResource(id = R.string.search) },
                    color = AppTheme.colors.hintColor
                )
            }

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .animateClickable(onBack),
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "back icon",
                tint = AppTheme.colors.hintColor
            )
        }

        LazyRow(
            reverseLayout = true,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
        ) {
            item {
                AdsFilterItem(
                    title = stringResource(id = R.string.filters),
                    isSelected = adsFilter != null,
                    icon = R.drawable.ic_filter,
                    onClick = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnFilter)) }
                )
            }
            item {
                AdsFilterItem(
                    title = adsFilter?.category?.name ?: stringResource(id = R.string.categories),
                    isVisibleClose = adsFilter?.category?.name != null,
                    icon = R.drawable.ic_category,
                    onClose = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnCategory(
                                    true
                                )
                            )
                        )
                    },
                    onClick = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnCategory(
                                    false
                                )
                            )
                        )
                    }
                )
            }
            item {
                AdsFilterItem(
                    title = adsFilter?.neighbourHood?.name
                        ?: stringResource(id = R.string.choose_neighbourhood),
                    isVisibleClose = adsFilter?.neighbourHood?.name != null,
                    onClose = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnNeighbourhood(
                                    true
                                )
                            )
                        )
                    },
                    onClick = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnNeighbourhood(
                                    false
                                )
                            )
                        )
                    }

                )
            }
            item {
                AdsFilterItem(
                    title = adsFilter?.price ?: stringResource(id = R.string.price),
                    isVisibleClose = adsFilter?.price != null,
                    onClose = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnPrice(true)
                            )
                        )
                    },
                    onClick = {
                        onAction(
                            AdsUiEvent.OnFilterClickType(
                                FilterClickType.OnPrice(false)
                            )
                        )
                    }
                )
            }
            adsFilter?.parameters?.takeIf {
                it.isNotEmpty()
            }?.let { parameters ->
                items(items = parameters)
                { parameter ->
                    AdsFilterItem(
                        title = parameter.answer ?: parameter.name,
                        isVisibleClose = parameter.answer != null,
                        onClose = {
                            onAction(
                                AdsUiEvent.OnFilterClickType(
                                    FilterClickType.OnParameter(
                                        parameter,
                                        isRemove = true
                                    )
                                )
                            )
                        },
                        onClick = {
                            onAction(
                                AdsUiEvent.OnFilterClickType(
                                    FilterClickType.OnParameter(
                                        parameter,
                                        isRemove = false
                                    )
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ToolbarPreview() {
    AppTheme {
        AdsToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            searchText = "آپارتمان",
            cityName = "مشهد",
            onCity = {},
            onSearch = {},
            onBack = {},
            adsFilter = null
        )
    }
}

@PreviewLightDark
@Composable
private fun ToolbarSelectedPreview() {
    AppTheme {
        AdsToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            searchText = "آپارتمان",
            cityName = "مشهد",
            onCity = {},
            onSearch = {},
            onBack = {},
            adsFilter = AdsFilter(
                category = FakeData.provideCategories().first()
            )
        )
    }
}