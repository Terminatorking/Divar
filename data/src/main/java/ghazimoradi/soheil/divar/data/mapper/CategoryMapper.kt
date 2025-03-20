package ghazimoradi.soheil.divar.data.mapper

import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.network.dto.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        icon = this.icon,
        children = this.children.map {
            it.toDomain()
        }
    )
}