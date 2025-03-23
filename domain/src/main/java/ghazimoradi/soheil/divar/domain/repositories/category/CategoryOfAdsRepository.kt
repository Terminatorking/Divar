package ghazimoradi.soheil.divar.domain.repositories.category

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import kotlinx.coroutines.flow.Flow

interface CategoryOfAdsRepository {

    suspend fun getCategoriesOfAds(searchText: String, cityId: Long): Flow<DataResult<List<CategoryOfAds>>>

}