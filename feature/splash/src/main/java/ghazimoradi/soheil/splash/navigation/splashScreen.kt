package ghazimoradi.soheil.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.splash.SplashScreen

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen(
    onMoveToAuth: () -> Unit,
    onMoveToLocation: () -> Unit,
) {
    composable(
        route = splashRoute,
    ) {
        SplashScreen(
            onMoveToAuth = onMoveToAuth,
            onMoveToLocation = onMoveToLocation
        )
    }
}