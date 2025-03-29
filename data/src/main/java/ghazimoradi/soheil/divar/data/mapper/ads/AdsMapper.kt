package ghazimoradi.soheil.divar.data.mapper.ads

import ghazimoradi.soheil.divar.data.mapper.parameter.toDomain
import ghazimoradi.soheil.divar.data.mapper.category.toDomain
import ghazimoradi.soheil.divar.data.mapper.image.toDomain
import ghazimoradi.soheil.divar.data.mapper.neighbourhood.toDomain
import ghazimoradi.soheil.divar.data.mapper.user.toDomain
import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.ads.CreateAdsParam
import ghazimoradi.soheil.divar.network.dto.ads.AdsResponse
import ghazimoradi.soheil.divar.network.dto.ads.AdsSummaryResponse
import ghazimoradi.soheil.divar.network.dto.ads.CreateAdsRequest
import ghazimoradi.soheil.divar.network.dto.ads.ParameterAnswerRequest

fun AdsSummaryResponse.toDomain(): AdsSummary {
    return AdsSummary(
        id = id,
        title = title,
        price = price,
        neighbourHood = neighbourhoodResponse.toDomain(),
        previewImage = previewImage?.toDomain(),
        createAt = createdAt
    )
}

fun AdsResponse.toDomain(): Ads {
    return Ads(
        id = id,
        title = title,
        description = description,
        price = price,
        neighbourHood = neighbourhood.toDomain(),
        user = user.toDomain(),
        category = category.toDomain(),
        images = images.map {
            it.toDomain()
        },
        answers = answers.map {
            it.toDomain()
        },
        createAt = createAt,
        updatedAt = updatedAt
    )
}

fun CreateAdsParam.toRequest(): CreateAdsRequest {
    return CreateAdsRequest(
        id = null,
        title = title,
        description = description,
        price = price,
        neighbourhoodId = 1,
        categoryId = category!!.id,
        answers = parameters.map {
            ParameterAnswerRequest(
                answer = it.answer.toString(),
                parameterId = it.id
            )
        }
    )
}