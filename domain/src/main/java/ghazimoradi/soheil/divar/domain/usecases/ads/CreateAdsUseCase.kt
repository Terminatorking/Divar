package ghazimoradi.soheil.divar.domain.usecases.ads

import ghazimoradi.soheil.divar.domain.model.ads.CreateAdsParam
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsRepository
import javax.inject.Inject

class CreateAdsUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        createAdsParam: CreateAdsParam
    ) = repo.createAds(
        createAdsParam = createAdsParam
    )
}