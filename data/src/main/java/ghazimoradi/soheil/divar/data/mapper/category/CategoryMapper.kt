package ghazimoradi.soheil.divar.data.mapper.category

import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.network.constant.BASE_URL_IMG
import ghazimoradi.soheil.divar.network.dto.category.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        icon = "$BASE_URL_IMG${this.icon}",
        children = this.children.map {categoryResponse->
            categoryResponse.toDomain()
        }
    )
}