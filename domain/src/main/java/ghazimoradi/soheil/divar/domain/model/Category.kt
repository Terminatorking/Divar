package ghazimoradi.soheil.divar.domain.model

data class Category(
    val id: Long,
    val name: String,
    val icon: String,
    val children: List<Category>
)
