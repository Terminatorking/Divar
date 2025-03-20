package ghazimoradi.soheil.divar.network.model

data class SuccessResponse<T>(
    val data: T?,
    val status: Status = Status.Success,
    val message: String = ""
)
