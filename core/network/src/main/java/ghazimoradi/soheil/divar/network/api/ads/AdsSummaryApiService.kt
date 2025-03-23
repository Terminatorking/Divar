package ghazimoradi.soheil.divar.network.api.ads

import ghazimoradi.soheil.divar.network.dto.ads.AdsSummaryResponse
import ghazimoradi.soheil.divar.network.dto.ads.GetAdsRequest
import ghazimoradi.soheil.divar.network.dto.paginate.PagingResponse
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AdsSummaryApiService {

    @POST("v1/ads/filter")
    suspend fun getAdsSummary(
        @Body getAdsRequest: GetAdsRequest
    ): SuccessResponse<PagingResponse<List<AdsSummaryResponse>>>

}