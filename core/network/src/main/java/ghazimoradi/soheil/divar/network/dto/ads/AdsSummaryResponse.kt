package ghazimoradi.soheil.divar.network.dto.ads

import ghazimoradi.soheil.divar.network.dto.image.ImageResponse
import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse
import kotlinx.serialization.Serializable

@Serializable
data class AdsSummaryResponse(
    val id: Long,
    val title: String,
    val price: String,
    val neighbourhoodResponse: NeighbourhoodResponse,
    val previewImage: ImageResponse,
    val createdAt: String,
)