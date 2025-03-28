package ghazimoradi.soheil.divar.network.api.category

import ghazimoradi.soheil.divar.network.dto.category.CategoryResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("category")
    suspend fun getCategories(): SuccessResponse<List<CategoryResponse>>
}