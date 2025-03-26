package ghazimoradi.soheil.create_ads.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.texts.LabelMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.extension.dashedBorder
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.utils.coilRounded
import ghazimoradi.soheil.divar.ui.extension.eLog

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.Image,
    title: Int = R.string.image,
    path: String,
    onClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    path.eLog("ImageItem")
    if (path.isEmpty()) {
        Column(
            modifier = modifier
                .animateClickable(onClick)
                .dashedBorder(
                    strokeWidth = 1.dp,
                    color = AppTheme.colors.hintColor,
                    cornerRadiusDp = 12.dp
                )
                .padding(8.dp)
                .wrapContentWidth(unbounded = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size((screenWidth.value / 3).dp - 42.dp),
                imageVector = imageVector,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(AppTheme.colors.hintColor)
            )

            LabelMediumText(
                modifier = Modifier,
                text = stringResource(id = title), color = AppTheme.colors.hintColor
            )
        }
    } else {
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = coilRounded(data = path, radiusInDp = 12f),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        ImageItem(onClick = {}, path = "")
    }
}