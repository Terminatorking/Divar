package ghazimoradi.soheil.divar.domain.model.ads

import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood

data class AdsSummary(
    val id: Long,
    val title: String,
    val price: String,
    val neighbourHood: NeighbourHood,
    val previewImage: Image?,
    val createAt: String? = null,
)