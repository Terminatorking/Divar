package ghazimoradi.soheil.divar.domain.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Long,
    val name: String,
    val icon: String,
    val children: List<Category>
)
