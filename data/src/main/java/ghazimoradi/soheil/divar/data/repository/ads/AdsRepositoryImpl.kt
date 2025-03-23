package ghazimoradi.soheil.divar.data.repository.ads

import ghazimoradi.soheil.divar.data.mapper.ads.toDomain
import ghazimoradi.soheil.divar.data.mapper.ads.toRequest
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.NullRequestBody
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.domain.model.ads.CreateAdsParam
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsRepository
import ghazimoradi.soheil.divar.network.api.ads.AdsApiService
import ghazimoradi.soheil.divar.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val apiService: AdsApiService
) : AdsRepository {
    override suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>> = flow {
        safeCall {
            apiService.getAdsDetail(id)
        }.onSuccess { data ->
            emit(DataResult.Success(data.toDomain()))
        }.onFailure  {
            emit(DataResult.Failure(it))
        }
    }

    override suspend fun createAds(createAdsParam: CreateAdsParam): Flow<DataResult<Unit>> = flow {
        val images: MutableList<MultipartBody.Part> = mutableListOf()
        createAdsParam.images.filter { it.isNotEmpty() }.forEachIndexed { index, path ->
            images.add(
                MultipartBody.Part.createFormData(
                    name = "images",
                    filename = path.substring(path.lastIndexOf("/") + 1),
                    body = File(path).asRequestBody("image/*".toMediaTypeOrNull())
                )
            )
        }

        val adsRequest = createAdsParam.toRequest().toJson()?.toRequestBody("text/plain".toMediaTypeOrNull())

        if (adsRequest == null) {
            emit(DataResult.Failure(NullRequestBody()))
        } else {
            safeCall {
                apiService.createAds(
                    images = images,
                    adsRequest = adsRequest
                )
            }.onSuccess {
                emit(DataResult.Success(Unit))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }

    }


}