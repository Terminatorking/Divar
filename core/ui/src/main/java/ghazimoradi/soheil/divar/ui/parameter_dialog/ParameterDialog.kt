package ghazimoradi.soheil.divar.ui.parameter_dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.divar.domain.model.parameter.Parameter
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.animateClickable
import ghazimoradi.soheil.divar.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParameterDialog(
    modifier: Modifier = Modifier,
    parameter: Parameter,
    onDismiss: () -> Unit,
    onSelect: (Parameter) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = AppTheme.colors.backgroundColor
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            parameter.acceptedOptions?.let { acceptList ->
                items(acceptList.size)
                { index ->
                    BodyMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateClickable {
                                onSelect(parameter.copy(answer = acceptList[index]))
                            },
                        textAlign = TextAlign.Center,
                        text = acceptList[index]
                    )

                    if (index != acceptList.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }
}