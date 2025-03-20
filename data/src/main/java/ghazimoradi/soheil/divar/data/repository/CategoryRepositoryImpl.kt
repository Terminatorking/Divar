package ghazimoradi.soheil.divar.data.repository

import ghazimoradi.soheil.divar.data.mapper.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.repositories.CategoryRepository
import ghazimoradi.soheil.divar.network.api.CategoryApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApiService
) : CategoryRepository {

    override suspend fun getCategories(): Flow<DataResult<List<Category>>> = flow {
        safeCall { apiService.getCategories() }
            .onSuccess { categoryResponseList ->
                emit(
                    DataResult.Success(
                        data = categoryResponseList.map { categoryResponse ->
                            categoryResponse.toDomain()
                        }
                    )
                )
            }.onFailure { apiError ->
                emit(DataResult.Failure(apiError = apiError))
            }
    }
}