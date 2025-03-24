package ghazimoradi.soheil.divar.domain.usecases.ads

import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsSummaryRepository
import javax.inject.Inject

class GetAdsSummaryUseCase @Inject constructor(
    private val repository: AdsSummaryRepository
) {
    suspend operator fun invoke(adsFilter: AdsFilter, page: Int, cityId: Long) =
        repository.getAdsSummary(page = page, cityId = cityId, adsFilter = adsFilter)
}