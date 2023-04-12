package com.junnyland.play.chatroom.gateway.out.message

import com.junnyland.play.chatroom.domain.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


interface Sender {
    fun send(message: Message)
    fun broadcast(message: Message): Message

    @RestController
    @CrossOrigin
    @RequestMapping("/sender")
    class KafkaSender(
        private val sender: KafkaTemplate<String,Message>,
        @Value("\${chatroom.path}") private val path: String,
    ) : Sender {
        @PostMapping("/message")
        override fun send(
            @RequestBody message: Message
        ) {
            val get = sender
                .send(path, message)
                .get()
            println("get = ${get}")
        }

        @SendTo("/chatroom")
        @MessageMapping("/sendMessage")
        override fun broadcast(@Payload message: Message) = message
    }

}