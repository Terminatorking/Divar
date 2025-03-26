package ghazimoradi.soheil.divar.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.divar.category.CategoryScreen
import ghazimoradi.soheil.divar.domain.model.category.Category

const val categoryRoute = "category_route"
fun NavGraphBuilder.categoryScreen(
    onCategory: (Category) -> Unit
) {
    composable(
        route = categoryRoute,
    ) {
        CategoryScreen(onCategory = onCategory)
    }
}

fun NavController.navigateToCategory() {
    navigate(categoryRoute)
}
