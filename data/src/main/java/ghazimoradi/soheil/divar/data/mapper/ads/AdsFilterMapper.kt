package ghazimoradi.soheil.divar.data.mapper.ads

import ghazimoradi.soheil.divar.data.mapper.parameter.toAnswerRequest
import ghazimoradi.soheil.divar.domain.model.filter.AdsFilter
import ghazimoradi.soheil.divar.network.dto.ads.GetAdsRequest

fun AdsFilter.toRequest(cityId: Long, page: Int): GetAdsRequest {
    val params = parameters?.mapNotNull { it.toAnswerRequest() }
    return GetAdsRequest(
        categoryId = category?.id,
        neighbourhoodId = neighbourHood?.id,
        cityId = cityId,
        price = price,
        parameters = if (params.isNullOrEmpty()) null else params,
        searchText = searchText,
        page = page
    )
}