package ghazimoradi.soheil.main.fake_data

import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.main.model.BottomBarItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

object MainFakeData {
    fun provideBottomBars(): ImmutableList<BottomBarItem> {
        return listOf(
            BottomBarItem(R.string.ads, R.drawable.ic_home, ""),
            BottomBarItem(R.string.category, R.drawable.ic_category, ""),
            BottomBarItem(R.string.create_ads, R.drawable.ic_plus, ""),
            BottomBarItem(R.string.chat, R.drawable.ic_chat, ""),
            BottomBarItem(R.string.my_divar, R.drawable.ic_user, "")
        ).toImmutableList()
    }
}