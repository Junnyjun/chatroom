package com.junnyland.play.config.messaging

import com.junnyland.play.chatroom.domain.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@EnableKafka
@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") val bootstrapServers: String,
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, Message> = DefaultKafkaProducerFactory(mapOf<String, String>(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java.name,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.name,
        ))


    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Message> = KafkaTemplate(producerFactory())
}
