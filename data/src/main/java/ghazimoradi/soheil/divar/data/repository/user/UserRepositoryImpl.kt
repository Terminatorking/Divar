package ghazimoradi.soheil.divar.data.repository.user

import android.content.SharedPreferences
import ghazimoradi.soheil.divar.data.mapper.user.toDomain
import ghazimoradi.soheil.divar.data.utils.safeCall
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.model.user.User
import ghazimoradi.soheil.divar.domain.repositories.user.UserRepository
import ghazimoradi.soheil.divar.network.api.user.UserApiService
import ghazimoradi.soheil.divar.network.dto.user.UserRequest
import ghazimoradi.soheil.divar.secure_shared_pref.SharedPrefConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import androidx.core.content.edit

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {

    override suspend fun login(
        mobile: String,
        password: String,
    ): Flow<DataResult<User>> = flow {
        safeCall {
            apiService.login(
                UserRequest(mobile, password)
            )
        }.onSuccess { data ->
            saveToken(data.token)
            emit(
                DataResult.Success(
                    data.toDomain()
                )
            )
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }

    override suspend fun register(
        mobile: String,
        password: String,
        repeatPassword: String,
    ): Flow<DataResult<User>> = flow {
        safeCall {
            apiService.register(
                UserRequest(
                    mobile = mobile,
                    password = password,
                    repeatPassword = repeatPassword
                )
            )
        }
            .onSuccess { data ->
                saveToken(data.token)
                emit(DataResult.Success(data.toDomain()))

            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun isLogin(): Flow<Boolean> = flow {
        emit(!sharedPreferences.getString(SharedPrefConstants.TOKEN, null).isNullOrEmpty())
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(SharedPrefConstants.TOKEN, token)
        }
    }
}