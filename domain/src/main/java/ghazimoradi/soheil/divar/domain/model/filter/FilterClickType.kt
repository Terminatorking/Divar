package ghazimoradi.soheil.divar.domain.model.filter

import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
sealed class FilterClickType {
    @Serializable
    data object OnFilter : FilterClickType()
    data class OnCategory(val isRemove: Boolean) : FilterClickType()
    data class OnNeighborhood(val isRemove: Boolean) : FilterClickType()
    data class OnPrice(val isRemove: Boolean) : FilterClickType()
    data class OnCategoryToShowAds(val category: Category) : FilterClickType()
    data class OnParameter(val parameter: Parameter, val isRemove: Boolean) : FilterClickType()
}