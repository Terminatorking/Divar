package ghazimoradi.soheil.divar.data.mapper.image

import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.network.dto.image.ImageResponse

fun ImageResponse.toDomain(): Image {
    return Image(path = "")
}