package ghazimoradi.soheil.divar.domain.model.location

data class Province(
    val id: Long,
    val name: String,
    val cities: List<City>? = null
)