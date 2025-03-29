package ghazimoradi.soheil.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.baseModifier
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun ProfileScreen(
) {
    Column(
        modifier = Modifier.baseModifier(),
        verticalArrangement = Arrangement.Center
    ) {
        BodyMediumText(
            text = "Profile Screen ",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        ProfileScreen()
    }
}