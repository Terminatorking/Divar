package ghazimoradi.soheil.ads.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ghazimoradi.soheil.ads.AdsScreen
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.divar.utils.toJson

const val adsRoute = "ads_route/{fromScreen}"

fun NavGraphBuilder.adsScreen(
    onCity: () -> Unit,
    onBack: () -> Unit,
    onSearch: (FromScreen) -> Unit,
    onFilter: (FromScreen) -> Unit
) {
    composable(
        route = adsRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {
        AdsScreen (
            onBack = onBack,
            onSearch = onSearch,
            onCity = onCity,
            onFilter = onFilter
        )
    }
}

fun NavController.navigateToAds(fromScreen: FromScreen) {
    adsRoute.replace("{fromScreen}", fromScreen.toJson()!!).eLog("**")
    navigate(adsRoute.replace("{fromScreen}", fromScreen.toJson()!!))
}
