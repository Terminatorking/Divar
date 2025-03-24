package ghazimoradi.soheil.divar.domain.usecases.location

import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import javax.inject.Inject

class SaveNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(neighborhood: NeighbourHood) {
        return repo.saveNeighborhood(neighborhood)
    }
}