package ghazimoradi.soheil.divar.network.dto.ads

import kotlinx.serialization.Serializable

@Serializable
data class CreateAdsRequest(
    val id: Long? = 0,
    val title: String,
    val description: String,
    val price: String,
    val neighbourhoodId: Long,
    val categoryId: Long,
    val answers: List<ParameterAnswerRequest>
)
