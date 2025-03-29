package ghazimoradi.soheil.divar.network.dto.ads

import ghazimoradi.soheil.divar.network.dto.image.ImageResponse
import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsSummaryResponse(
    val id: Long,
    val title: String,
    val price: String,
    @SerialName("neighborhood")  val neighbourhoodResponse: NeighbourhoodResponse,
    val previewImage: ImageResponse?,
    @SerialName("createAt") val createdAt: String,
)