package com.junnyland.play.chatroom.gateway.out.message

import com.junnyland.play.chatroom.domain.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

interface Sender {
    fun send(message: Message)

    @Component
    class KafkaSender(
        private val kafkaTemplate: KafkaTemplate<String, String>,
    ): Sender {
        override fun send(message: Message) {
            println("KafkaSender: $message")
            kafkaTemplate.send(message.target,message.message)
        }
    }
}