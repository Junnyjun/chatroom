package com.junnyland.play.chatroom.gateway.`in`

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController

@RestController
class SocketEnter {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(
        @Payload message: ChatMessage,
    ) {
        println("sendMessage")
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(
        @Payload message: ChatMessage,
        headerAccessor: SimpMessageHeaderAccessor,
    ) {
        println("addUser")
    }

    data class ChatMessage(
        val sender: String,
        val content: String,
        val type: MessageType,
    )

    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}