package ghazimoradi.soheil.divar.network.dto.location

import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse
import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val id: Long,
    val name: String,
    val neighbourhoods: List<NeighbourhoodResponse>? = null
)