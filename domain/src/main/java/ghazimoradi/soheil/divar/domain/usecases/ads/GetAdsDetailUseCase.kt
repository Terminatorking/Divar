package ghazimoradi.soheil.divar.domain.usecases.ads

import ghazimoradi.soheil.divar.domain.repositories.ads.AdsRepository
import javax.inject.Inject

class GetAdsDetailUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        id: Long
    ) = repo.getAdsDetail(
        id = id
    )
}