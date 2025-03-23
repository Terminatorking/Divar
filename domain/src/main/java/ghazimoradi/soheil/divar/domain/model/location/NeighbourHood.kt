package ghazimoradi.soheil.divar.domain.model.location

import kotlinx.serialization.Serializable

@Serializable
data class NeighbourHood(
    val id: Long,
    val name: String,
)