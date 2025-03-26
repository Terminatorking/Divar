package ghazimoradi.soheil.location.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable


@Composable
fun NeighborhoodItem(
    neighborhood: NeighbourHood,
    onClick: () -> Unit
) {
    BodyMediumText(
        modifier = Modifier.fillMaxWidth().animateClickable(onClick),
        text = neighborhood.name,
        textAlign = TextAlign.Start
    )
}