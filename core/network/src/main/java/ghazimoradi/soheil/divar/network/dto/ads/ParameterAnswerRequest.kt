package ghazimoradi.soheil.divar.network.dto.ads

import kotlinx.serialization.Serializable

@Serializable
data class ParameterAnswerRequest(
    val answer: String,
    val parameterId: Long,
)