package com.junnyland.play.chatroom.gateway.`in`.message

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

interface MessageEnter {
    fun enter(headers: MessageHeaders, message: String)

    @Component
    class KafkaMessageEnter(
        @Value("\${spring.kafka.bootstrap-servers}") private val bootstrapServers: String,
    ): MessageEnter{

        private val comsumer:KafkaConsumer<String,String> = KafkaConsumer(
            mapOf<String,String>(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG to "junnyland",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.MAX_POLL_RECORDS_CONFIG to "500",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
            )
        )

        @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
        override fun enter(
            @Headers headers: MessageHeaders,
            @Payload message: String
        ) {
            let {  }
            println("KafkaMessageEnter: $message")
        }
    }
}