package ghazimoradi.soheil.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.baseModifier

@Composable
fun ProfileScreen(
) {
    Column(
        modifier = Modifier.baseModifier(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BodyMediumText(text = "Profile Screen top")
        BodyMediumText(text = "Profile Screen bottom")
    }
}
