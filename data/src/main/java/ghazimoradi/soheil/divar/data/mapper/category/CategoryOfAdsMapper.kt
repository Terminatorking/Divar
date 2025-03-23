package ghazimoradi.soheil.divar.data.mapper.category

import ghazimoradi.soheil.divar.domain.model.category.CategoryOfAds
import ghazimoradi.soheil.divar.network.dto.category.CategoryOfAdsResponse

fun CategoryOfAdsResponse.toDomain(): CategoryOfAds {
    return CategoryOfAds(
        categoryName = categoryName,
        categoryId = categoryId,
        adsCount = adsCount,
        adsTitle = adsTitle
    )
}