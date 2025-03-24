package ghazimoradi.soheil.divar.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.ui.core.texts.LabelMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.utils.svgCoil
import ghazimoradi.soheil.divar.ui.R

@Composable
fun CategoryHomeItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (Category) -> Unit
) {

    Column(
        modifier = Modifier
            .then(modifier)
            .animateClickable { onClick(category) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        category.icon.takeIf { icon ->
            icon.isNotBlank()
        }?.let { icon ->
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = svgCoil(icon),
                contentDescription = "category icon",
                colorFilter = ColorFilter.tint(color = AppTheme.colors.iconColor),
            )
        } ?: run {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_no_camera),
                contentDescription = "ads_with_no_image",
                tint = AppTheme.colors.hintColor
            )
        }
        LabelMediumText(text = category.name, textAlign = TextAlign.Center)
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryHomeItem(
            modifier = Modifier
                .background(AppTheme.colors.backgroundColor),
            category = FakeData.provideCategories().first(),
            onClick = {}
        )
    }
}