package ghazimoradi.soheil.divar.domain.model.user

data class User(
    val name: String,
    val family: String,
    val email: String,
    val token: String,
    val mobile: String,
    val createAt: String?,
    val updatedAt: String?,
)