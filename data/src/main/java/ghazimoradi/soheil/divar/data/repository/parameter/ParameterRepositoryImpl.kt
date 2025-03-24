package ghazimoradi.soheil.divar.data.repository.parameter

import android.content.SharedPreferences
import ghazimoradi.soheil.divar.data.mapper.parameter.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import ghazimoradi.soheil.divar.domain.repositories.parameter.ParameterRepository
import ghazimoradi.soheil.divar.network.api.parameter.ParameterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ParameterRepositoryImpl @Inject constructor(
    private val apiService: ParameterApiService,
    private val sharedPreferences: SharedPreferences
) : ParameterRepository {
    override suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>> = flow {
        safeCall { apiService.getParameters(categoryId) }
            .onSuccess { data ->
                emit(
                    DataResult.Success(
                        data.map {
                            it.toDomain()
                        }
                    )
                )
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }
}