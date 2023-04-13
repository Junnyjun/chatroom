package com.junnyland.play.chatroom.gateway.`in`.message

import com.junnyland.play.chatroom.domain.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller


interface Receiver {
    fun enter(message: Message)

    @Component
    class KafkaReceiver(
        private val template: SimpMessagingTemplate,
    ) : Receiver {

        @KafkaListener(
            topics = ["chatroom"],
            groupId = "junnyland",
            containerFactory = "kafkaListenerContainerFactory"
        )
        override fun enter(message: Message) {
            template.convertAndSend("/topic/group/${message.roomName}", message)
        }
    }
}