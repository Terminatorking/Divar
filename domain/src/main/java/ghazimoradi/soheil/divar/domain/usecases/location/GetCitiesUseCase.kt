package ghazimoradi.soheil.divar.domain.usecases.location

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(includeNeighborhoods: Boolean = false): Flow<DataResult<List<City>>> {
        return if (includeNeighborhoods)
            repo.getCitiesWidthNeighborhoods()
        else
            repo.getCities()
    }
}