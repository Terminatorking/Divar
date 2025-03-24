package ghazimoradi.soheil.divar.domain.repositories.ads

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface AdsSummaryRepository {

    suspend fun getAdsSummary(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>>
}