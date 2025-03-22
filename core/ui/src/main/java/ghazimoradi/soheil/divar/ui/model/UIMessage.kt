package ghazimoradi.soheil.divar.ui.model

enum class MessageType { Network, System }
enum class MessageStatus { Success, Failure }

sealed class UIMessageContent {
    data class IntMessage(val value: Int) : UIMessageContent()
    data class StringMessage(val value: String) : UIMessageContent()
}

data class UIMessage(
    val messageType: MessageType,
    val status: MessageStatus,
    val content: UIMessageContent
) {
    constructor(
        messageType: MessageType = MessageType.Network,
        status: MessageStatus = MessageStatus.Failure,
        intValue: Int
    ) : this(
        messageType = messageType,
        status = status,
        content = UIMessageContent.IntMessage(intValue)
    )
    constructor(
        messageType: MessageType = MessageType.Network,
        status: MessageStatus = MessageStatus.Failure,
        stringValue: String
    ) : this(
        messageType = messageType,
        status = status,
        content = UIMessageContent.StringMessage(stringValue)
    )
}