package ghazimoradi.soheil.divar.category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ghazimoradi.soheil.divar.domain.fake_data.FakeData
import ghazimoradi.soheil.divar.domain.model.Category
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.utils.*

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier.animateClickable {
            onClick?.invoke()
        },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End)
    ) {
        if (category.children.isNotEmpty()) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                tint = AppTheme.colors.iconColor
            )
        }

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = category.name,
        )
        category.icon.takeIf {
            it.isNotBlank()
        }?.let { icon ->
            AsyncImage(
                modifier = Modifier.size(20.dp),
                model = svgCoil(icon),
                contentDescription = "category icon",
                colorFilter = ColorFilter.tint(color = AppTheme.colors.iconColor)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryItem(category = FakeData.provideCategories()[0])
    }
}