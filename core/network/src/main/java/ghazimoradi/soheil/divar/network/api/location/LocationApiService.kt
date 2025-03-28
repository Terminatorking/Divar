package ghazimoradi.soheil.divar.network.api.location

import ghazimoradi.soheil.divar.network.dto.location.CityResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("city")
    suspend fun getCities(): SuccessResponse<List<CityResponse>>

    @GET("city")
    suspend fun getCitiesWithNeighbourhood(
        @Query("includeNeighborhoods") includeNeighbourhoods: Boolean = true
    ): SuccessResponse<List<CityResponse>>
}