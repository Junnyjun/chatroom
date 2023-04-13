package com.junnyland.play.chatroom.gateway.out.message

import com.junnyland.play.chatroom.SendMessage
import com.junnyland.play.chatroom.domain.Message
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*


interface Sender {
    fun send(message: Message)
    fun broadcast(message: Message, roomName: String): Message

    @CrossOrigin
    @RestController
    @RequestMapping("/sender")
    class KafkaSender(
        private val sendMessage: SendMessage
    ) : Sender {
        val logger = LoggerFactory.getLogger("sender")

        @PostMapping("/message")
        override fun send(
            @RequestBody message: Message
        ) {
            sendMessage.execute(message)
        }

        @SendTo("/topic/group/{roomName}")
        @MessageMapping("/sendMessage")
        override fun broadcast(
            @Payload message: Message,
            @DestinationVariable("roomName") roomName: String
        ): Message {
            logger.info("room : $roomName , message : $message")
            return message
        }
    }

}