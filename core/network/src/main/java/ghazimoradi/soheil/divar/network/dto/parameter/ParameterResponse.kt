package ghazimoradi.soheil.divar.network.dto.parameter

import kotlinx.serialization.Serializable

@Serializable
data class ParameterResponse(
    val id: Long,

    val name: String,

    val dataType: DataTypeResponse,

    val acceptedOptions: List<String>? = null,
)

enum class DataTypeResponse {
    StringInput,
    NumberInput,
    FloatInput,
    CheckBoxInput,
    FixedOption
}