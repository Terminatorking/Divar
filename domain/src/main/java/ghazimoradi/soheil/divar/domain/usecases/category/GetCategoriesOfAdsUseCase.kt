package ghazimoradi.soheil.divar.domain.usecases.category

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import ghazimoradi.soheil.divar.domain.repositories.category.CategoryOfAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesOfAdsUseCase @Inject constructor(
    private val repo: CategoryOfAdsRepository
) {
    suspend operator fun invoke(searchText: String, cityId: Long): Flow<DataResult<List<CategoryOfAds>>> {
        return repo.getCategoriesOfAds(searchText, cityId)
    }
}