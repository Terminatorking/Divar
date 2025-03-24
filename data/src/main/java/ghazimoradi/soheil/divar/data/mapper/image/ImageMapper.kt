package ghazimoradi.soheil.divar.data.mapper.image

import ghazimoradi.soheil.divar.network.constant.BASE_URL_IMG
import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.network.dto.image.ImageResponse

fun ImageResponse.toDomain(): Image {
    return Image(path = BASE_URL_IMG + imageUrl(this.path))
}

private fun imageUrl(path: String): String {
    val splitText: List<String> = path.split("\\\\")
    val lastIndex = splitText.lastIndex
    return splitText[lastIndex]
}