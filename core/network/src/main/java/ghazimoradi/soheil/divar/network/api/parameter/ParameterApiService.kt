package ghazimoradi.soheil.divar.network.api.parameter

import ghazimoradi.soheil.divar.network.dto.parameter.ParameterResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ParameterApiService {

    @GET("parameter")
    suspend fun getParameters(
        @Query("categoryId") categoryId: Long = 0,
    ): SuccessResponse<List<ParameterResponse>>

}