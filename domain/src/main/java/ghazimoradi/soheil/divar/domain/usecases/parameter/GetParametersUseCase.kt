package ghazimoradi.soheil.divar.domain.usecases.parameter

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import ghazimoradi.soheil.divar.domain.repositories.parameter.ParameterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetParametersUseCase @Inject constructor(
    private val repo: ParameterRepository
) {
    suspend operator fun invoke(categoryId: Long): Flow<DataResult<List<Parameter>>> {
        return repo.getParameters(categoryId)
    }
}