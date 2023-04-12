package com.junnyland.play.chatroom.gateway.`in`.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.junnyland.play.chatroom.domain.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller


interface Receiver {
    fun enter(message: Message)

    @Controller
    class KafkaReceiver(
        private val template: SimpMessagingTemplate,
        @Value("\${chatroom.path}") private val path: String,
    ) : Receiver {

        @KafkaListener(topics = ["chatroom"], groupId = "junnyland", containerFactory = "kafkaListenerContainerFactory")
        override fun enter(
            message: Message,
        ) {
            println("KafkaConsumerConfig: $message")
            template.convertAndSend(path, message)
        }
    }
}