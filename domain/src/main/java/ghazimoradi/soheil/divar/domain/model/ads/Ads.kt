package ghazimoradi.soheil.divar.domain.model.ads

import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.model.parameter.ParameterAnswer
import ghazimoradi.soheil.divar.domain.model.user.User

data class Ads(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    val neighbourHood: NeighbourHood,
    val user: User,
    val category: Category,
    val images: List<Image>,
    val answers: List<ParameterAnswer>,
    val createAt: String? = null,
    val updatedAt: String? = null,
)