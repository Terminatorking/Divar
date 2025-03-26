package ghazimoradi.soheil.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ghazimoradi.soheil.chat.ChatScreen

const val chatRoute = "chat_route"
fun NavGraphBuilder.chatScreen(
) {
    composable(
        route = chatRoute,
    ) {
        ChatScreen()
    }
}

fun NavController.navigateToChat() {
    navigate(chatRoute)
}
