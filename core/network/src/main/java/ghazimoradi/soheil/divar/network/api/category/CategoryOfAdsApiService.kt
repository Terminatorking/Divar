package ghazimoradi.soheil.divar.network.api.category

import ghazimoradi.soheil.divar.network.dto.category.CategoryOfAdsResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryOfAdsApiService {

    @GET("v1/ads/categories_of_ads")
    suspend fun getCategoriesOfAds(
        @Query("searchText") searchText: String,
        @Query("cityId") cityId: Long,
    ): SuccessResponse<List<CategoryOfAdsResponse>>

}