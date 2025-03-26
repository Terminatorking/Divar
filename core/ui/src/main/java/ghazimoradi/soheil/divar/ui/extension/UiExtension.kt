package ghazimoradi.soheil.divar.ui.extension

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import java.time.Duration
import java.time.Instant
import java.util.Locale

@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount < 3) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                val viewportHeight = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount
                        && lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
            }
        }
    }
    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}

@Composable
fun Dp.toComposePx(): Float {
    val density = LocalDensity.current.density
    return density * value
}

@Composable
fun Int.toComposeDp(): Dp {
    val density = LocalDensity.current.density
    return (this / density).dp
}

fun Modifier.baseModifier(padding: Dp = 16.dp): Modifier = composed {
    this
        .fillMaxSize()
        .imePadding()
        .background(AppTheme.colors.backgroundColor)
        .statusBarsPadding()
        .navigationBarsPadding()
        .padding(padding)
}

fun Modifier.animateClickable(onClick: (() -> Unit)): Modifier = composed {
    this
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
}

fun String.toPrice(): String {
    this.toIntOrNull()?.let { number ->
        return String.format(Locale.getDefault(), "%,d", number)
    } ?: return ""
}

fun Context.getActivity(): ComponentActivity? {
    return when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }
}

fun NavController.runWithLifecycleAware(block: NavController.() -> Unit) {
    if (currentBackStackEntry?.getLifecycle()?.currentState == Lifecycle.State.RESUMED)
        block()
}

fun String?.relativeTime(): String {
    if (this.isNullOrEmpty()) return ""
    val inputInstant = Instant.parse(this)
    val nowInstant = Instant.now()

    val duration = Duration.between(inputInstant, nowInstant)
    val seconds = duration.seconds

    return when {
        seconds < 15 * 60 -> "لحظاتی پیش"
        seconds < 30 * 60 -> "یک ربع پیش"
        seconds < 60 * 60 -> "نیم ساعت پیش"
        seconds < 2 * 60 * 60 -> "یک ساعت پیش"
        seconds < 24 * 60 * 60 -> "${seconds / 3600} ساعت پیش"
        seconds > 24 * 60 * 60 -> "${seconds / (3600 * 24)} روز پیش"
        else -> ""
    }
}

fun Any?.eLog(tag: String = "MyLog", plusTag: String = "") {
    Log.e("$tag $plusTag", this.toString())
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)

@Composable
fun Int.CreateSpace(modifier: Modifier = Modifier) {
    Spacer(
        modifier = Modifier.size(this.dp).then(modifier),
    )
}
