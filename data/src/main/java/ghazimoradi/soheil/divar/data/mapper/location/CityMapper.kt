package ghazimoradi.soheil.divar.data.mapper.location

import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.network.dto.location.CityResponse

fun CityResponse.toDomain(): City {
    return City(
        id = id,
        name = name,
        neighborhoods = neighborhoods?.map {
            it.toDomain()
        }
    )
}