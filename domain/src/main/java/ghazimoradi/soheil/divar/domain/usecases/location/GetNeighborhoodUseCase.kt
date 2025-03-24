package ghazimoradi.soheil.divar.domain.usecases.location

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(): Flow<DataResult<NeighbourHood>> {
        return repo.getUserNeighborhood()
    }
}