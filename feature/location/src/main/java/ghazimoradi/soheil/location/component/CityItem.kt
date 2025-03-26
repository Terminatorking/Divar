package ghazimoradi.soheil.location.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit
) {
    BodyMediumText(
        modifier = Modifier.fillMaxWidth().animateClickable(onClick),
        text = city.name,
        textAlign = TextAlign.Start
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme  {
        CityItem(
            city = FakeData.provideCities().first()
        ) {}
    }
}