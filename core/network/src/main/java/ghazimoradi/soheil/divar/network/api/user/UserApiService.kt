package ghazimoradi.soheil.divar.network.api.user

import ghazimoradi.soheil.divar.network.dto.user.UserResponse
import ghazimoradi.soheil.divar.network.dto.user.UserRequest
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("user/login")
    suspend fun login(
        @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>

    @POST("user/register")
    suspend fun register(
       @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>
}