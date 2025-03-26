package ghazimoradi.soheil.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.profile.ProfileScreen

const val profileRoute = "profile_route"
fun NavGraphBuilder.profileScreen(
) {
    composable(
        route = profileRoute,
    ) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile() {
    navigate(profileRoute)
}
