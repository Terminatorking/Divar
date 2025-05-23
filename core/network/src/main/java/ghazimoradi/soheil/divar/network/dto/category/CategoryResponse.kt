package ghazimoradi.soheil.divar.network.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: Long,
    val name: String,
    val icon: String,
    val children: List<CategoryResponse>
)
