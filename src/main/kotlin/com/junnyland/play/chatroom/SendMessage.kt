package com.junnyland.play.chatroom

import com.junnyland.play.chatroom.domain.Message
import com.junnyland.play.chatroom.gateway.out.repository.ChatMessageRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

fun interface SendMessage {
    fun execute(message: Message)

    @Service
    class SendMessageUsecase(
        private val sender: KafkaTemplate<String, Message>,
        private val repository: ChatMessageRepository
    ): SendMessage{
        override fun execute(message: Message) {
            sender.send("chatroom", message)
            repository.saveAsync(message)
        }
    }
}