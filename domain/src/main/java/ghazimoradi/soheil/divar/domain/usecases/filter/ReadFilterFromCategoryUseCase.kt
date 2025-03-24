package ghazimoradi.soheil.divar.domain.usecases.filter

import ghazimoradi.soheil.divar.domain.repositories.filter.FilterRepository
import javax.inject.Inject


class ReadFilterFromCategoryUseCase @Inject constructor(
    private val repo: FilterRepository
) {
    suspend operator fun invoke() = repo.readFilterFromCategory()
}