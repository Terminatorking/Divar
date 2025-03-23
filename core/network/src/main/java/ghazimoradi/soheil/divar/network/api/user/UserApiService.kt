package ghazimoradi.soheil.divar.network.api.user

import ghazimoradi.soheil.divar.network.dto.user.UserResponse
import ghazimoradi.soheil.divar.network.dto.user.UserRequest
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("v1/user/login")
    suspend fun login(
        @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>

    @POST("v1/user/register")
    suspend fun register(
       @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>

}