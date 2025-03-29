package ghazimoradi.soheil.divar.data.mapper.image

import ghazimoradi.soheil.divar.data.BuildConfig
import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.network.dto.image.ImageResponse
import ghazimoradi.soheil.divar.ui.extension.eLog

fun ImageResponse.toDomain(): Image {
    (BuildConfig.BaseUrl + this.path).eLog(tag = "image")
    return Image(path = BuildConfig.BaseUrl + this.path)
}
