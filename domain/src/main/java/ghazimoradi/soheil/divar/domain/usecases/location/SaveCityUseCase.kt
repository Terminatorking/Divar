package ghazimoradi.soheil.divar.domain.usecases.location

import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(city: City) {
        return repo.saveCity(city)
    }
}