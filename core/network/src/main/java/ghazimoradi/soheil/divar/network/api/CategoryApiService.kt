package ghazimoradi.soheil.divar.network.api

import ghazimoradi.soheil.divar.network.dto.CategoryResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("category")
    suspend fun getCategories(): SuccessResponse<List<CategoryResponse>>

}