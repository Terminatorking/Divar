package ghazimoradi.soheil.ads_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.ads_detail.component.FullScreenSlider
import ghazimoradi.soheil.ads_detail.component.SliderSection
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.domain.model.parameter.ParameterAnswer
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.button.AppButton
import ghazimoradi.soheil.divar.ui.core.texts.BodyLargeText
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.core.texts.LabelMediumText
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.extension.relativeTime
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun AdsDetailScreen(
    vm: AdsDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.baseModifier(),
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                strokeWidth = 2.dp,
                color = AppTheme.colors.titleColor
            )
        }
    } else if (uiState.showFullScreenSlider) {
        FullScreenSlider(
            ads = uiState.ads!!,
            onDismiss = { vm.onTriggerEvent(AdsDetailUiEvent.ShowFullScreenSlider(false)) }
        )
    } else if (uiState.ads == null) {
        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .animateClickable { vm.onTriggerEvent(AdsDetailUiEvent.OnRefresh) },
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.try_again)
        )
    } else {
        AdsDetailScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            onAction = {
                vm.onTriggerEvent(it)
            },
            ads = uiState.ads,
            onBack = onBack
        )
    }

    UiMessageScreen(
        shared = vm.uiMessage
    )
}


@Composable
fun AdsDetailScreenContent(
    modifier: Modifier = Modifier,
    ads: Ads,
    onAction: OnAction,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SliderSection(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
                .background(Color.Gray),
            ads = ads,
            onBack = onBack,
            onShare = { onAction(AdsDetailUiEvent.OnShareClick(context)) },
            onAction = onAction
        )
        CenterSection(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(16.dp),
            ads = ads
        )

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.itemColor)
                .padding(16.dp),
            text = R.string.contact_information,
            onClick = {}
        )

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CenterSection(modifier: Modifier, ads: Ads) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {

        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {

            LabelMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = ads.category.name,
                color = AppTheme.colors.primaryColor
            )
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .padding(bottom = 4.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "category",
                tint = AppTheme.colors.iconColor
            )
        }

        BodyLargeText(
            Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = ads.title
        )

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "${ads.createAt.relativeTime()} ${ads.neighbourHood.name}"
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 0.5.dp
        )

        FlowColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ads.answers.forEachIndexed { index, parameterAnswer ->
                ParameterAnswerItem(parameterAnswer)

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 0.5.dp
                )
            }
        }

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.description)
        )
        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = ads.description
        )
    }
}

@Composable
fun ParameterAnswerItem(parameterAnswer: ParameterAnswer) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BodyMediumText(text = parameterAnswer.answer)

        BodyMediumText(text = parameterAnswer.parameter.name)
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AdsDetailScreenContent(
        modifier = Modifier.baseModifier(),
        onAction = {},
        ads = FakeData.provideAds()
    )
}
