package ghazimoradi.soheil.divar.data.mapper.image

import ghazimoradi.soheil.divar.data.BuildConfig
import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.network.dto.image.ImageResponse

fun ImageResponse.toDomain(): Image {
    return Image(path = BuildConfig.BaseUrl + imageUrl(this.path))
}

private fun imageUrl(path: String): String {
    val splitText: List<String> = path.split("\\\\")
    val lastIndex = splitText.lastIndex
    return splitText[lastIndex]
}