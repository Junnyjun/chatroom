package com.junnyland.play.chatroom.gateway.out.message

import com.junnyland.play.chatroom.domain.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller

private const val TOPIC = "chatroom"

interface Sender {
    fun send(message: Message)

    @Controller
    class KafkaSender(
        private val sender: SimpMessageSendingOperations,
    ) : Sender {
        @MessageMapping("/chat/send")
        override fun send(message: Message) {
            val target: String = "/topic/${message.roomId}"

            if (Message.MessageType.JOIN == message.type) {
                sender.convertAndSend(target, "${message.sender} joined")
            }
            sender.convertAndSend("/topic/${message.roomId}", message)
        }
    }
}