package com.junnyland.play.chatroom.domain

data class Message(
    val message: String,
    val roomId: String,
    val sender: String,
    val type: MessageType,
){
    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
