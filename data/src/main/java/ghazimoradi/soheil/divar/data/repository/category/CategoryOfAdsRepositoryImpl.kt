package ghazimoradi.soheil.divar.data.repository.category
import ghazimoradi.soheil.divar.data.mapper.category.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.repositories.category.CategoryOfAdsRepository
import ghazimoradi.soheil.divar.network.api.category.CategoryOfAdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryOfAdsRepositoryImpl @Inject constructor(
    private val apiService: CategoryOfAdsApiService
) : CategoryOfAdsRepository {

    override suspend fun getCategoriesOfAds(
        searchText: String,
        cityId: Long
    ): Flow<DataResult<List<CategoryOfAds>>> = flow {
        safeCall { apiService.getCategoriesOfAds(searchText, cityId) }
            .onSuccess { data ->
                emit(
                    DataResult.Success(
                        data.map {
                            it.toDomain()
                        },
                    )
                )
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

}