package ghazimoradi.soheil.divar.domain.repositories.location

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCities(): Flow<DataResult<List<City>>>
    suspend fun getCitiesWidthNeighbourhoods(): Flow<DataResult<List<City>>>
    suspend fun saveCity(city: City)
    suspend fun saveNeighbourhood(neighbourhood: NeighbourHood)
    suspend fun getUserCity(): Flow<DataResult<City>>
    suspend fun getUserNeighbourhood(): Flow<DataResult<NeighbourHood>>
}