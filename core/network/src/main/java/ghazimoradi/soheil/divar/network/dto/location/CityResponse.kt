package ghazimoradi.soheil.divar.network.dto.location

import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val id: Long,
    val name: String,
    @SerialName("neighborhoods") val neighbourhoods: List<NeighbourhoodResponse>? = null
)