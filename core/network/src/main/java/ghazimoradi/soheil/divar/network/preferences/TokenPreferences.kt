package ghazimoradi.soheil.divar.network.preferences

import android.content.SharedPreferences
import ghazimoradi.soheil.divar.secure_shared_pref.SharedPrefConstants
import javax.inject.Inject

class TokenPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun readToken(): String {
        return sharedPreferences.getString(SharedPrefConstants.TOKEN, "") ?: ""
    }
}