package ghazimoradi.soheil.divar.navigation

import ghazimoradi.soheil.chat.navigation.chatRoute
import ghazimoradi.soheil.create_ads.navigation.createAdsRoute
import ghazimoradi.soheil.divar.category.navigation.categoryRoute
import ghazimoradi.soheil.divar.home.navigation.homeRoute
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.main.model.BottomBarItem
import ghazimoradi.soheil.profile.navigation.profileRoute

fun provideBottomBars(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(R.string.my_divar, R.drawable.ic_user, profileRoute),
        BottomBarItem(R.string.chat, R.drawable.ic_chat, chatRoute),
        BottomBarItem(R.string.create_ads, R.drawable.ic_plus, createAdsRoute),
        BottomBarItem(R.string.category, R.drawable.ic_category, categoryRoute),
        BottomBarItem(R.string.ads, R.drawable.ic_home, homeRoute)
    )
}