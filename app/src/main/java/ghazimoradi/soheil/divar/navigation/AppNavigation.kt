package ghazimoradi.soheil.divar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ghazimoradi.soheil.ads.navigation.navigateToAds
import ghazimoradi.soheil.ads_detail.navigation.adsDetailScreen
import ghazimoradi.soheil.ads_detail.navigation.navigateToAdsDetail
import ghazimoradi.soheil.auth.navigation.authScreen
import ghazimoradi.soheil.auth.navigation.navigateToAuth
import ghazimoradi.soheil.create_ads.navigation.createAdsRoute
import ghazimoradi.soheil.create_ads.navigation.createAdsScreen
import ghazimoradi.soheil.create_ads.navigation.navigateToCreateAds
import ghazimoradi.soheil.divar.domain.model.location.LocationScreenType
import ghazimoradi.soheil.divar.ui.extension.runWithLifecycleAware
import ghazimoradi.soheil.filter.navigation.filterScreen
import ghazimoradi.soheil.filter.navigation.navigateToFilter
import ghazimoradi.soheil.location.navigation.locationScreen
import ghazimoradi.soheil.location.navigation.navigateToLocation
import ghazimoradi.soheil.main.navigation.mainScreen
import ghazimoradi.soheil.main.navigation.navigateToMain
import ghazimoradi.soheil.search.navigation.navigateToSearch
import ghazimoradi.soheil.search.navigation.searchScreen
import ghazimoradi.soheil.splash.navigation.splashRoute
import ghazimoradi.soheil.splash.navigation.splashScreen

@Composable
fun AppNavigation() {

    val rootNavController = rememberNavController()
    val mainNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = splashRoute
    ) {
        splashScreen(
            onMoveToAuth = {
                rootNavController.runWithLifecycleAware {
                    rootNavController.navigateToAuth()
                }
            },
            onMoveToLocation = {
                rootNavController.runWithLifecycleAware {
                    rootNavController.navigateToLocation(
                        LocationScreenType.FromLogin
                    )
                }
            }
        )

        mainScreen(
            bottomBarItems = provideBottomBars(),
            mainNavigation = {
                MainNavigation(
                    navController = mainNavController,
                    onSearch = {
                        rootNavController.runWithLifecycleAware {
                            navigateToSearch(it)
                        }
                    },
                    onCity = {},
                    onFilter = {
                        rootNavController.navigateToFilter(it)
                    },
                    onAdsClick = {
                        rootNavController.navigateToAdsDetail(it)
                    }
                )
            },
            onChangeBottomBar = {
                it.route.takeIf { bottomBarItem -> bottomBarItem.isNotEmpty() }?.let { route ->
                    mainNavController.runWithLifecycleAware {
                        if (it.route == createAdsRoute) {
                            rootNavController.navigateToCreateAds()
                        } else {
                            navigate(route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(mainNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                // Avoid multiple copies of the same destination when
                                // re-selecting the same item
                                launchSingleTop = true

                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }
                        }
                    }
                }
            }
        )

        locationScreen(
            onBack = {
                rootNavController.popBackStack()
            },
            onMoveToMain = {
                rootNavController.runWithLifecycleAware {
                    rootNavController.navigateToMain()
                }
            }
        )
        searchScreen(
            onSelected = {
                rootNavController.popBackStack()
                mainNavController.navigateToAds(it)
            },
            onBack = {
                rootNavController.popBackStack()
            }
        )

        filterScreen(
            onBack = {
                rootNavController.popBackStack()
            },
            onSaveFilter = {
                rootNavController.popBackStack()
                mainNavController.popBackStack()
                mainNavController.navigateToAds(it)
            }
        )

        adsDetailScreen (
            onBack = {
                rootNavController.runWithLifecycleAware {
                    popBackStack()
                }
            },
        )

        authScreen(
            navigateToLocation = {
                rootNavController.navigateToLocation(LocationScreenType.FromLogin)
            },
        )

        createAdsScreen(
            onLocation = {
                rootNavController.runWithLifecycleAware { navigateToLocation(LocationScreenType.FromCreateAds) }
            },
            onBack = {
                rootNavController.runWithLifecycleAware { popBackStack() }
            },
        )
    }
}