package ghazimoradi.soheil.divar.data.mapper.parameter

import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import ghazimoradi.soheil.divar.network.dto.ads.ParameterAnswerRequest
import ghazimoradi.soheil.divar.network.dto.parameter.ParameterResponse

fun ParameterResponse.toDomain(): Parameter {
    return Parameter(
        id = id,
        name = name,
        dataType = dataType.toDomain(),
        acceptedOptions = acceptedOptions,
    )
}

fun Parameter.toAnswerRequest(): ParameterAnswerRequest? {
    if (answer.isNullOrEmpty()) return null
    return ParameterAnswerRequest(
        answer = answer ?: "", parameterId = id
    )
}


