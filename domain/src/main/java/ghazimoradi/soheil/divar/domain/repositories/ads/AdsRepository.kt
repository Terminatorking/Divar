package ghazimoradi.soheil.divar.domain.repositories.ads

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.domain.model.ads.CreateAdsParam
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>>

    suspend fun createAds(createAdsParam: CreateAdsParam): Flow<DataResult<Unit>>
}