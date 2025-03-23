package ghazimoradi.soheil.divar.domain.repositories.user

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(
        mobile: String,
        password: String,
    ): Flow<DataResult<User>>

    suspend fun register(
        mobile: String,
        password: String,
        repeatPassword: String,
    ): Flow<DataResult<User>>

    suspend fun isLogin(): Flow<Boolean>
}