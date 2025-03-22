package ghazimoradi.soheil.divar.data.mapper

import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.network.constant.BASE_URL_SVG
import ghazimoradi.soheil.divar.network.dto.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        icon = "$BASE_URL_SVG${this.icon}",
        children = this.children.map {categoryResponse->
            categoryResponse.toDomain()
        }
    )
}