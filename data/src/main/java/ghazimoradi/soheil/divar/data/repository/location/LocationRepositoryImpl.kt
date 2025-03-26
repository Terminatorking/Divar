package ghazimoradi.soheil.divar.data.repository.location

import android.content.SharedPreferences
import ghazimoradi.soheil.divar.data.mapper.location.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import ghazimoradi.soheil.divar.network.api.location.LocationApiService
import ghazimoradi.soheil.divar.secure_shared_pref.SharedPrefConstants
import ghazimoradi.soheil.divar.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import androidx.core.content.edit
import ghazimoradi.soheil.divar.domain.model.NotFoundError
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.utils.fromJson

class LocationRepositoryImpl @Inject constructor(
    private val apiService: LocationApiService,
    private val sharedPreferences: SharedPreferences
) : LocationRepository {
    override suspend fun getCities(): Flow<DataResult<List<City>>> = flow {
        safeCall { apiService.getCities() }
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

    override suspend fun getCitiesWidthNeighbourhoods(): Flow<DataResult<List<City>>> = flow {
        safeCall { apiService.getCitiesWithNeighbourhood() }
            .onSuccess { data ->
                emit(
                    DataResult.Success(
                        data.map {
                            it.toDomain()
                        }
                    ),
                )
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun saveCity(city: City) {
        city.toJson()?.let {
            sharedPreferences.edit {
                putString(SharedPrefConstants.USER_CITY, it)
            }
        }
    }

    override suspend fun saveNeighbourhood(neighbourhood: NeighbourHood) {
        neighbourhood.toJson()?.let {
            sharedPreferences.edit {
                putString(SharedPrefConstants.USER_NEIGHBOURHOOD, it)
            }
        }
    }

    override suspend fun getUserCity(): Flow<DataResult<City>> = flow {
        sharedPreferences.getString(SharedPrefConstants.USER_CITY, null)?.fromJson<City?>()?.let {
            emit(DataResult.Success(it))
        } ?: run {
            emit(DataResult.Failure(NotFoundError(404)))
        }
    }

    override suspend fun getUserNeighbourhood(): Flow<DataResult<NeighbourHood>> = flow {
        sharedPreferences.getString(SharedPrefConstants.USER_NEIGHBOURHOOD, null)
            ?.fromJson<NeighbourHood?>()?.let {
                emit(DataResult.Success(it))
            } ?: run {
            emit(DataResult.Failure(NotFoundError(404)))
        }
    }
}