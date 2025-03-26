package ghazimoradi.soheil.divar.ui.core.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.divar.ui.R
import ghazimoradi.soheil.divar.ui.core.texts.TitleMediumText
import ghazimoradi.soheil.divar.ui.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primaryColor,
        ),
        shape = AppTheme.shapes.roundSmall,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                strokeWidth = 2.dp,
                color = Color.White
            )
        } else {
            TitleMediumText(
                text = stringResource(id = text),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = R.string.save_filters,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun PreviewLoading() {
    AppTheme {
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = R.string.save_filters,
            onClick = {},
            isLoading = true
        )
    }
}