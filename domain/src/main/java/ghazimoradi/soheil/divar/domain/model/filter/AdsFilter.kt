package ghazimoradi.soheil.divar.domain.model.filter

import androidx.compose.runtime.Immutable
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class AdsFilter(
    val category: Category? = null,
    val neighbourHood: NeighbourHood? = null,
    val price: String? = null,
    val parameters: List<Parameter>? = null,
    val searchText: String = "",
    val focus: FilterClickType? = null
)