package ghazimoradi.soheil.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.auth.AuthScreen

const val authRoute = "auth_route"
fun NavGraphBuilder.authScreen(
    navigateToMain : () -> Unit
) {
    composable(
        route = authRoute,
    ) {
        AuthScreen(navigateToMain = navigateToMain)
    }
}

fun NavController.navigateToAuth() {
    navigate(authRoute)
}
