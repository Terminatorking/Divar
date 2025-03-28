package ghazimoradi.soheil.divar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    val data: T?,
    val status: Status = Status.Success,
    val message: String = ""
)