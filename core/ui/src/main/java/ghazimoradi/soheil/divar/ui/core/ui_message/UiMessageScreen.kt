package ghazimoradi.soheil.divar.ui.core.ui_message

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.divar.ui.core.texts.BodyMediumText
import ghazimoradi.soheil.divar.ui.extension.eLog
import ghazimoradi.soheil.divar.ui.model.MessageStatus
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.model.UIMessageContent
import ghazimoradi.soheil.divar.ui.theme.AppTheme
import ghazimoradi.soheil.divar.ui.theme.White
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun UiMessageScreen(
    modifier: Modifier = Modifier,
    shared: SharedFlow<UIMessage?>
) {
    val context = LocalContext.current
    var uiMessage: UIMessage? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(shared) {
        shared.collect { uiMessageCollect ->
            uiMessage = uiMessageCollect
            uiMessage.eLog("uiMessage")
        }
    }

    val messageText: String by remember(uiMessage) {
        derivedStateOf {
            uiMessage?.content?.let { content ->
                when (content) {
                    is UIMessageContent.IntMessage -> context.getString(content.value)
                    is UIMessageContent.StringMessage -> content.value
                }
            } ?: run {
                ""
            }
        }
    }

    AnimatedVisibility(visible = uiMessage != null) {
        MessageBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .statusBarsPadding()
                .background(
                    color = when (uiMessage?.status) {
                        MessageStatus.Success -> AppTheme.colors.successColor
                        MessageStatus.Failure -> AppTheme.colors.errorColor
                        else -> AppTheme.colors.errorColor
                    }
                )
                .padding(8.dp)
                .then(modifier),
            messageText = messageText
        )
    }

}

@Composable
fun MessageBox(modifier: Modifier, messageText: String) {
    BodyMediumText(
        modifier = modifier,
        text = messageText,
        color = White
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        MessageBox(
            modifier = Modifier.background(AppTheme.colors.errorColor),
            messageText = "این یک پیغام خطاست"
        )
    }
}