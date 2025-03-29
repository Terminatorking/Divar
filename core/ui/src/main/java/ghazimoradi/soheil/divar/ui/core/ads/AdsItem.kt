package ghazimoradi.soheil.divar.ui.core.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.texts.BodyLargeText
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.relativeTime
import ghazimoradi.soheil.divar.ui.extension.toPrice
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.theme.Gray
import ghazimoradi.soheil.divar.ui.utils.coilRounded

@Composable
fun AdsItem(
    adsSummary: AdsSummary,
    onClick: (AdsSummary) -> Unit
) {
    Row(
        modifier = Modifier
            .animateClickable {
                onClick.invoke(adsSummary)
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        adsSummary.previewImage?.path?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .background(color = Gray, shape = AppTheme.shapes.roundSmall)
                    .clip(AppTheme.shapes.roundSmall),
                contentScale = ContentScale.Crop,
                model = coilRounded(data = image, radiusInDp = 0f),
                contentDescription = "ad's image"
            )
        } ?: run {
            Icon(
                painter = painterResource(id = R.drawable.ic_no_camera),
                contentDescription = "ads_with_no_image",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .background(color = Gray, shape = AppTheme.shapes.roundSmall)
                    .clip(AppTheme.shapes.roundSmall),
            )
        }

        Column(modifier = Modifier.weight(0.5f), horizontalAlignment = Alignment.End) {
            BodyLargeText(
                text = adsSummary.title,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            BodyMediumText(
                text = stringResource(id = R.string.as_new),
                color = AppTheme.colors.hintColor
            )
            BodyMediumText(
                text = adsSummary.price.toPrice(),
                color = AppTheme.colors.hintColor
            )
            BodyMediumText(
                text = adsSummary.createAt.relativeTime(),
                color = AppTheme.colors.hintColor
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        AdsItem(
            adsSummary = FakeData.provideAdsSummary().first()
        ) {}
    }
}