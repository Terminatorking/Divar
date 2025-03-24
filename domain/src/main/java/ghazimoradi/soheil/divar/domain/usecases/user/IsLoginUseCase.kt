package ghazimoradi.soheil.divar.domain.usecases.user

import ghazimoradi.soheil.divar.domain.repositories.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoginUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repo.isLogin()
    }
}