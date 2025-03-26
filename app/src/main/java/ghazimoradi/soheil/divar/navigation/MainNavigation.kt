package ghazimoradi.soheil.divar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ghazimoradi.soheil.ads.navigation.adsScreen
import ghazimoradi.soheil.ads.navigation.navigateToAds
import ghazimoradi.soheil.chat.navigation.chatScreen
import ghazimoradi.soheil.divar.category.navigation.categoryScreen
import ghazimoradi.soheil.divar.home.navigation.homeRoute
import ghazimoradi.soheil.divar.home.navigation.homeScreen
import ghazimoradi.soheil.divar.ui.extension.runWithLifecycleAware
import ghazimoradi.soheil.divar.ui.model.FromScreen
import ghazimoradi.soheil.main.navigation.mainRoute
import ghazimoradi.soheil.profile.navigation.profileScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    onSearch: (FromScreen) -> Unit,
    onFilter: (FromScreen) -> Unit,
    onAdsClick: (Long) -> Unit,
    onCity: () -> Unit
) {
    NavHost(
        navController = navController,
        route = mainRoute,
        startDestination = homeRoute
    )
    {
        homeScreen(
            onCity = {},
            onSearch = { onSearch(FromScreen.Home) },
            onSelectedCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(FromScreen.Home)
                }
            },
            onAdsClick = onAdsClick
        )

        categoryScreen(
            onCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(FromScreen.Category)
                }
            }
        )
        chatScreen()
        profileScreen()
        adsScreen(
            onBack = { navController.popBackStack() },
            onSearch = onSearch,
            onCity = onCity,
            onFilter = onFilter
        )
    }
}