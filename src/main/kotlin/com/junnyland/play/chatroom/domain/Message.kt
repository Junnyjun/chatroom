package com.junnyland.play.chatroom.domain

import java.time.LocalDateTime.now

data class Message(
    val message: String,
    val roomName: String,
    val sender: String,
    val type: MessageType,
    val timestamp: String = now().toString()
){
    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}

