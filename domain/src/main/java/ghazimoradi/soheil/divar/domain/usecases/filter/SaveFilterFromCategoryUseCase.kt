package ghazimoradi.soheil.divar.domain.usecases.filter

import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.repositories.filter.FilterRepository
import javax.inject.Inject

class SaveFilterFromCategoryUseCase @Inject constructor(
    private val repo: FilterRepository
) {
    suspend operator fun invoke(adsFilter: AdsFilter?) = repo.saveFilterFromCategory(adsFilter)
}