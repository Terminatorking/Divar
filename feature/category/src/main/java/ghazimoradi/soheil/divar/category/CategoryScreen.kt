package ghazimoradi.soheil.divar.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    CategoryScreenContent(modifier = Modifier.fillMaxSize())
}

@Composable
fun CategoryScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryScreenContent(modifier = Modifier.fillMaxSize())
    }
}