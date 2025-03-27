package ghazimoradi.soheil.divar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ghazimoradi.soheil.divar.navigation.AppNavigation
import ghazimoradi.soheil.divar.ui.theme.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * This line is required before onCreate to ensure that
         * applyPostSplashScreenTheme (in theme) functions correctly.
         * https://stackoverflow.com/a/78445185/10180275
         * */
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            if (isSystemInDarkTheme()) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb())
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb())
                )
            }
            AppTheme {
                AppNavigation()
            }
        }
    }
}