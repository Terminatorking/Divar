package ghazimoradi.soheil.divar.domain.repositories

import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<DataResult<List<Category>>>
}