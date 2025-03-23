package ghazimoradi.soheil.divar.data.repository.ads

import ghazimoradi.soheil.divar.data.mapper.ads.toDomain
import ghazimoradi.soheil.divar.data.mapper.ads.toRequest
import ghazimoradi.soheil.divar.data.mapper.paginate.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsSummaryRepository
import ghazimoradi.soheil.divar.network.api.ads.AdsSummaryApiService
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdsSummaryRepositoryImpl @Inject constructor(
    private val apiService: AdsSummaryApiService
) : AdsSummaryRepository {

    override suspend fun getAdsSummary(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>> = flow {
        safeCall {
            apiService.getAdsSummary(
                adsFilter.toRequest(cityId = cityId, page = page)
            )
        }.onSuccess { data ->
            val paging = data.toDomain(
                contentMapper = {
                    it.map {
                        adsSummaryResponse -> adsSummaryResponse.toDomain()
                    }.toImmutableList()
                }
            )
            emit(DataResult.Success(paging))
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }


}