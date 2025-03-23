package ghazimoradi.soheil.divar.data.mapper.location

import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse

fun NeighbourhoodResponse.toDomain(): NeighbourHood {
    return NeighbourHood(id = id, name = name)
}