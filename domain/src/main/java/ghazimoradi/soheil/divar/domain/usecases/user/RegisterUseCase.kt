package ghazimoradi.soheil.divar.domain.usecases.user

import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.user.User
import ghazimoradi.soheil.divar.domain.repositories.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(mobile: String, password: String, repeatPassword: String): Flow<DataResult<User>> {
        return repo.register(mobile, password, repeatPassword)
    }
}