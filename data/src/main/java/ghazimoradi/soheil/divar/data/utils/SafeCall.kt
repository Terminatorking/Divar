package ghazimoradi.soheil.divar.data.utils

import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.domain.model.ApiError
import ghazimoradi.soheil.divar.domain.model.DataResult
import ghazimoradi.soheil.divar.domain.model.ServerError
import ghazimoradi.soheil.divar.network.model.FailureResponse
import ghazimoradi.soheil.divar.network.model.Status
import ghazimoradi.soheil.divar.network.model.SuccessResponse
import kotlinx.serialization.json.Json
import retrofit2.HttpException

suspend fun <T> safeCall(
    execute: suspend () -> SuccessResponse<T>
): DataResult<T> {
    return try {
        val response = execute.invoke()
        if (response.status == Status.Success) {
            DataResult.Success(response.data!!, response.message)
        } else {
            DataResult.Failure(ServerError(504))
        }
    } catch (e: Throwable) {
        e.message.eLog()
        DataResult.Failure(getApiError(e))
    }
}

fun getApiError(throwable: Throwable): ApiError {
    when (throwable) {
        is HttpException -> {
            if (throwable.code() == 500) {
                return ServerError(500, message = throwable.message())
            }else if (throwable.code() == 404)
                return ServerError(404, message = throwable.message())

            val bodyError = throwable.response()?.errorBody()?.string() ?: ""
            val failureResponse = Json.decodeFromString<FailureResponse>(bodyError)
            return failureResponse.toApiError()
        }

        else -> {
            return object : ApiError {
                override val httpStatus: Int = 598
                override val message: String = throwable.message ?: "An unknown error occurred"
            }
        }
    }
}
