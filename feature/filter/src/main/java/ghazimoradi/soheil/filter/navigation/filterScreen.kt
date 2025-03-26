package ghazimoradi.soheil.filter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.utils.toJson
import ghazimoradi.soheil.filter.FilterScreen

const val filterRoute = "filter_route/{fromScreen}"
fun NavGraphBuilder.filterScreen(
    onBack: () -> Unit,
    onSaveFilter: (FromScreen) -> Unit,
) {
    composable(
        route = filterRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {
        FilterScreen(onBack = onBack, onSaveFilter = onSaveFilter)
    }
}

fun NavController.navigateToFilter(
    fromScreen: FromScreen
) {
    navigate(
        filterRoute.replace("{fromScreen}", fromScreen.toJson()!!)
    )
}
