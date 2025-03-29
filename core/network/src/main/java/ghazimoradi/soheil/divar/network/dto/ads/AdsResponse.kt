package ghazimoradi.soheil.divar.network.dto.ads

import ghazimoradi.soheil.divar.network.dto.category.CategoryResponse
import ghazimoradi.soheil.divar.network.dto.image.ImageResponse
import ghazimoradi.soheil.divar.network.dto.neighbourhood.NeighbourhoodResponse
import ghazimoradi.soheil.divar.network.dto.parameter.ParameterAnswerResponse
import ghazimoradi.soheil.divar.network.dto.user.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsResponse(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    @SerialName("neighborhood") val neighbourhood: NeighbourhoodResponse,
    val user: UserResponse,
    val category: CategoryResponse,
    val images: List<ImageResponse>,
    val answers: List<ParameterAnswerResponse>,
    val createAt: String? = null,
    val updatedAt: String? = null,
)