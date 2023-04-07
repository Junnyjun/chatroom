package com.junnyland.play.chatroom.gateway.out.message

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") val bootstrapServers: String,
    @Value("\${spring.kafka.producer.key-serializer}") val keySerializer: String,
    @Value("\${spring.kafka.producer.value-serializer}") val valueSerializer: String,
) {

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> =KafkaTemplate(DefaultKafkaProducerFactory(
        mapOf<String,String>(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to valueSerializer,
        )))
}