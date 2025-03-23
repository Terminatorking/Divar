package ghazimoradi.soheil.divar.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.ui.core.ui_message.UiMessageScreen
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    HomeScreenContent()
    UiMessageScreen(shared = viewModel.uiMessage)
}

@Composable
fun HomeScreenContent() {
    AppTheme {
        Scaffold(
            modifier = Modifier.baseModifier(padding = 0.dp)
        ) { innerPadding ->
            innerPadding.eLog()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.backgroundColor)
            ) {

            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    HomeScreenContent()
}