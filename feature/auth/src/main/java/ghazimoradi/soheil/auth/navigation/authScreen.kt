package ghazimoradi.soheil.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.auth.AuthScreen

const val authRoute = "auth_route"
fun NavGraphBuilder.authScreen(
    navigateToLocation : () -> Unit,
) {
    composable(
        route = authRoute,
    ) {
        AuthScreen( navigateToLocation = navigateToLocation)
    }
}

fun NavController.navigateToAuth() {
    navigate(authRoute)
}
