package ghazimoradi.soheil.ads_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.ads_detail.AdsDetailUiEvent
import ghazimoradi.soheil.ads_detail.OnAction
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.CreateSpace
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.utils.coilRounded
import ghazimoradi.soheil.divar.ui.theme.White
import ghazimoradi.soheil.divar.ui.theme.Black
import ghazimoradi.soheil.divar.ui.theme.Gray

@Composable
fun SliderSection(
    modifier: Modifier,
    ads: Ads,
    onAction: OnAction,
    onBack: () -> Unit,
    onShare: () -> Unit = {}
) {
    val pagerState = rememberPagerState {
        ads.images.size
    }
    Box(modifier = modifier)
    {
        HorizontalPager(state = pagerState) { pageIndex ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = coilRounded(
                    data = ads.images[pageIndex].path,
                    radiusInDp = 0f
                ),
                contentDescription = ""
            )
        }

        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
                .animateClickable(onBack)
                .align(alignment = Alignment.TopEnd),
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Click to back",
            tint = White
        )

        Row(
            Modifier
                .align(alignment = Alignment.TopStart)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Icon(
                modifier = Modifier
                    .animateClickable { onShare() }
                    .size(24.dp),
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = White
            )

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.Print,
                contentDescription = "Print",
                tint = White
            )

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = White
            )
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .animateClickable {
                    onAction(
                        AdsDetailUiEvent.ShowFullScreenSlider(true)
                    )
                }
                .background(
                    color = Black.copy(alpha = 0.7f),
                    shape = AppTheme.shapes.roundMedium
                )
                .padding(vertical = 0.dp, horizontal = 4.dp)
                .align(alignment = Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Fullscreen,
                contentDescription = "",
                tint = White
            )
            BodyMediumText(
                modifier = Modifier.padding(top = 4.dp),
                text = ads.images.size.toString(),
                color = White
            )
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(vertical = 8.dp)
                .width(100.dp),
            pagerState = pagerState
        )
    }
}

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterHorizontally)
    ) {
        items(pagerState.pageCount) { index ->
            12.CreateSpace(
                modifier = Modifier
                    .then(
                        if (index == pagerState.currentPage) {
                            Modifier.background(
                                color = White,
                                shape = CircleShape
                            )
                        } else {
                            Modifier.background(
                                color = White.copy(alpha = 0.6f),
                                shape = CircleShape
                            )
                        }
                    ),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        Box(Modifier.baseModifier(0.dp))
        {
            SliderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .background(
                        Gray,
                        shape = AppTheme.shapes.roundSmall,
                    ),
                FakeData.provideAds(),
                onAction = {},
                onBack = {},
            )
        }
    }
}