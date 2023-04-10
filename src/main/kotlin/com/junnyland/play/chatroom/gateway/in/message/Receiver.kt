package com.junnyland.play.chatroom.gateway.`in`.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.junnyland.play.chatroom.domain.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

private const val TOPIC = "chatroom"
val deserializer = ObjectMapper()

interface Receiver {
    fun enter(message: Message)

    @Controller
    class KafkaReceiver(
        private val simpMessageTemplate: SimpMessagingTemplate,
    ) : Receiver {

        @KafkaListener(topics = [TOPIC])
        override fun enter(
            message: Message,
        ) {
            println("KafkaConsumerConfig: $message")
            simpMessageTemplate.convertAndSend(
                "${TOPIC}/${message.roomId}",
                deserializer.writeValueAsString(message)
            )
        }
    }

}