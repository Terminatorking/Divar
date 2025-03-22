package ghazimoradi.soheil.divar.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import ghazimoradi.soheil.divar.ui.extension.dLog
import ghazimoradi.soheil.divar.ui.extension.toComposePx
import coil.decode.SvgDecoder
import coil.request.CachePolicy

@Composable
fun coilCircle(data: Any): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data).memoryCachePolicy(CachePolicy.ENABLED)
        .listener(
            onError = { _, throwable ->
                // Log or handle the error
                throwable.throwable.message.dLog(tag = "coilError")
            }
        )
        .crossfade(true)
        .transformations(
            CircleCropTransformation()
        ).build()
}

@Composable
fun coilRounded(data: Any, radiusInDp: Float): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data).memoryCachePolicy(CachePolicy.ENABLED)
        .crossfade(true)
        .listener(
            onError = { _, throwable ->
                // Log or handle the error
                throwable.throwable.message.dLog(tag = "coilError")
            }
        )
        .transformations(
            RoundedCornersTransformation(
                radiusInDp.dp.toComposePx()
            ),
        ).build()
}

@Composable
fun coilRounded(
    data: Any,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomLeft: Float = 0f,
    bottomRight: Float = 0f
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data).memoryCachePolicy(CachePolicy.ENABLED)
        .listener(
            onError = { _, throwable ->
                // Log or handle the error
                throwable.throwable.message.dLog(tag = "coilError")
            }
        )
        .size(Size.ORIGINAL)
        .crossfade(true)
        .transformations(
            RoundedCornersTransformation(
                topLeft = topLeft.dp.toComposePx(),
                topRight = topRight.dp.toComposePx(),
                bottomLeft = bottomLeft.dp.toComposePx(),
                bottomRight = bottomRight.dp.toComposePx()
            ),
        ).build()
}

@Composable
fun defaultCoil(
    data: String,
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .listener(
            onError = { _, result ->
                result.throwable.message.dLog("coilError")
            },
        )
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
}

@Composable
fun svgCoil(
    data: String,
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data).memoryCachePolicy(CachePolicy.ENABLED)
        .listener(
            onError = { _, result ->
                result.throwable.message.dLog("coilError")
            },
        ).decoderFactory(SvgDecoder.Factory())
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
}