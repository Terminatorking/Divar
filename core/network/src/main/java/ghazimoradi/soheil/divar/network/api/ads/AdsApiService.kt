package ghazimoradi.soheil.divar.network.api.ads

import ghazimoradi.soheil.divar.network.dto.ads.AdsResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface AdsApiService {

    @GET("ads/detail")
    suspend fun getAdsDetail(
        @Query("id") id: Long
    ): SuccessResponse<AdsResponse>

    @POST("ads")
    @Multipart
    suspend fun createAds(
        @Part images: List<MultipartBody.Part>,
        @Part("ads") adsRequest: RequestBody
    ): SuccessResponse<AdsResponse>

}