package ghazimoradi.soheil.divar.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.divar.home.HomeScreen

const val homeRoute = "home_route"
fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeRoute,
    ) {
        HomeScreen()
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(route = homeRoute)
}