package ghazimoradi.soheil.divar.domain.repositories.parameter

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import kotlinx.coroutines.flow.Flow

interface ParameterRepository {
    suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>>
}