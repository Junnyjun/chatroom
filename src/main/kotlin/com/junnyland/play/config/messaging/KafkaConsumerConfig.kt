package com.junnyland.play.config.messaging

import com.junnyland.play.chatroom.domain.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") private val bootstrapServers: String,
) {

    @Bean
    fun concurrentKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> =
        ConcurrentKafkaListenerContainerFactory<String, Message>()
            .also { it.consumerFactory = consumerFactory() }

    private fun consumerFactory() = DefaultKafkaConsumerFactory(
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "junnyland",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization",
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG to "500",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
        ),
        StringDeserializer(),
        JsonDeserializer<Message>(Message::class.java)
            .also {
                it.setRemoveTypeHeaders(false)
                it.addTrustedPackages("*")
                it.setUseTypeMapperForKey(true)
            }
    )


}