package ghazimoradi.soheil.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.splash.SplashScreen

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen(
    onMoveToMain: () -> Unit,
    onMoveToLocation: () -> Unit,
) {
    composable(
        route = splashRoute,
    ) {
        SplashScreen(
            onMoveToMain = onMoveToMain,
            onMoveToLocation = onMoveToLocation
        )
    }
}

fun NavController.navigateToSplash() {
    navigate(splashRoute)
}
