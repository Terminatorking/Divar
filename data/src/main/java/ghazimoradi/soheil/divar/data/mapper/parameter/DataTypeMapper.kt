package ghazimoradi.soheil.divar.data.mapper.parameter

import ghazimoradi.soheil.divar.domain.model.parameter.DataType
import ghazimoradi.soheil.divar.network.dto.parameter.DataTypeResponse

fun DataTypeResponse.toDomain(): DataType {
    return when (this) {
        DataTypeResponse.StringInput -> DataType.StringInput
        DataTypeResponse.NumberInput -> DataType.NumberInput
        DataTypeResponse.FloatInput -> DataType.FloatInput
        DataTypeResponse.CheckBoxInput -> DataType.CheckBoxInput
        DataTypeResponse.FixedOption -> DataType.FixedOption
    }
}