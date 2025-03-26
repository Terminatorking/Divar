package ghazimoradi.soheil.divar.domain.model.ads

import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable

@Serializable
data class CreateAdsParam(
    val category: Category? = null,
    val neighbourHood: NeighbourHood? = null,
    val images: ImmutableList<String> = listOf("", "", "", "", "", "").toImmutableList(),
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val parameters: ImmutableList<Parameter> = listOf<Parameter>().toImmutableList()
)