package com.junnyland.play.chatroom.gateway.`in`.message

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload

interface MessageEnter {
    fun enter(headers: MessageHeaders, message: String)

    class KafkaMessageEnter(
        private val kafkaTemplate: KafkaTemplate<String, String>
    ): MessageEnter {

        @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
        override fun enter(
            @Headers headers: MessageHeaders,
            @Payload message: String
        ) {
            println("KafkaConsumerConfig: $message")
        }
    }

}