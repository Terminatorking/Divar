package ghazimoradi.soheil.divar.network.dto.ads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAdsRequest(
    val categoryId: Long?,
    @SerialName("neighborhoodId") val neighbourhoodId: Long?,
    val cityId: Long,
    val price: String?,
    val parameters: List<ParameterAnswerRequest>?,
    val searchText: String = "",
    val page: Int
)


