package ghazimoradi.soheil.divar.data.repository.filter

import ghazimoradi.soheil.divar.secure_shared_pref.SharedPrefConstants
import android.content.SharedPreferences
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.domain.repositories.filter.FilterRepository
import ghazimoradi.soheil.divar.utils.fromJson
import ghazimoradi.soheil.divar.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import androidx.core.content.edit

class FilterRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FilterRepository {
    override suspend fun saveFilterFromHome(adsFilter: AdsFilter?) {
        sharedPreferences.edit {
            putString(SharedPrefConstants.HOME_FILTER, adsFilter.toJson())
        }
    }

    override suspend fun saveFilterFromCategory(adsFilter: AdsFilter?) {
        sharedPreferences.edit{
            putString(SharedPrefConstants.CATEGORY_FILTER, adsFilter.toJson())
        }
    }

    override suspend fun readFilterFromHome(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstants.HOME_FILTER, null)?.fromJson<AdsFilter?>()
            ?.let { filter ->
                emit(filter)
            } ?: run { emit(null) }
    }

    override suspend fun readFilterFromCategory(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstants.CATEGORY_FILTER, null)
            ?.fromJson<AdsFilter?>()?.let { filter ->
            emit(filter)
        } ?: run { emit(null) }
    }
}