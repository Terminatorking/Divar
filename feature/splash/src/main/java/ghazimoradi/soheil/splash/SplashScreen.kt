package ghazimoradi.soheil.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.getActivity

@Composable
fun SplashScreen(
    vm: SplashViewModel = hiltViewModel(),
    onMoveToAuth: () -> Unit,
    onMoveToLocation: () -> Unit,
) {
    val uiState = vm.uiState.collectAsState().value
    LocalContext.current.getActivity()?.installSplashScreen()
    LaunchedEffect(key1 = uiState.userIsLogin) {
        if (uiState.userIsLogin) {
           onMoveToLocation()
        }else{
            onMoveToAuth()
        }
    }

    UiMessageScreen(shared = vm.uiMessage)
}