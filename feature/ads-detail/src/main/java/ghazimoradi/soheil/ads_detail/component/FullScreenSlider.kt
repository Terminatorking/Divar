package ghazimoradi.soheil.ads_detail.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.utils.coilRounded

@Composable
fun FullScreenSlider(ads: Ads, onDismiss: () -> Unit) {
    val pagerState = rememberPagerState {
        ads.images.size
    }
    BackHandler {
        onDismiss()
    }
    Box(modifier = Modifier.baseModifier(0.dp))
    {

        HorizontalPager(
            modifier = Modifier
                .align(alignment = Alignment.Center)
            ,
            state = pagerState,
        ) { pageIndex ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(unbounded = true),
                contentScale = ContentScale.Inside,
                model = coilRounded(
                    data = ads.images[pageIndex].path,
                    radiusInDp = 0f
                ),
                contentDescription = ""
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(vertical = 8.dp)
                .width(100.dp),
            pagerState = pagerState
        )

        Icon(
            modifier = Modifier
                .size(48.dp)
                .align(alignment = Alignment.TopEnd)
                .animateClickable(onDismiss)
                .padding(12.dp),
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.White
        )
    }
}