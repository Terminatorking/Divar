package ghazimoradi.soheil.divar.domain.repositories.filter

import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import kotlinx.coroutines.flow.Flow

interface FilterRepository {

    suspend fun saveFilterFromHome(adsFilter: AdsFilter?)
    suspend fun saveFilterFromCategory(adsFilter: AdsFilter?)
    suspend fun readFilterFromHome(): Flow<AdsFilter?>
    suspend fun readFilterFromCategory(): Flow<AdsFilter?>

}