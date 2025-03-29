package ghazimoradi.soheil.divar.domain.model.filter

import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
sealed class FilterClickType {
    @Serializable
    data object OnFilter : FilterClickType()
    @Serializable
    data class OnCategory(val isRemove: Boolean) : FilterClickType()
    @Serializable
    data class OnNeighbourhood(val isRemove: Boolean) : FilterClickType()
    @Serializable
    data class OnPrice(val isRemove: Boolean) : FilterClickType()
    @Serializable
    data class OnCategoryToShowAds(val category: Category) : FilterClickType()
    @Serializable
    data class OnParameter(val parameter: Parameter, val isRemove: Boolean) : FilterClickType()
}