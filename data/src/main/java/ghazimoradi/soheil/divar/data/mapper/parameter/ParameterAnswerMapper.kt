package ghazimoradi.soheil.divar.data.mapper.parameter

import ghazimoradi.soheil.divar.domain.model.parameter.ParameterAnswer
import ghazimoradi.soheil.divar.network.dto.parameter.ParameterAnswerResponse

fun ParameterAnswerResponse.toDomain(): ParameterAnswer {
    return ParameterAnswer(
        answer = answer,
        parameter = parameter.toDomain()
    )
}