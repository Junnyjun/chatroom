package com.junnyland.play.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.handler.TextWebSocketHandler

private val logger: Logger = LoggerFactory.getLogger("SoceketInterceptor")

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SocketHandler(), "/junnyland/chat").setAllowedOrigins("*")
            .withSockJS()
    }

    @Component
    class SocketHandler: TextWebSocketHandler(){
        override fun afterConnectionEstablished(session: WebSocketSession) {
            logger.info("afterConnectionEstablished: ${session.id}")
        }

        override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
            logger.info("handleTextMessage: ${session.id}")
            logger.info("handleTextMessage: ${message.payload}")
        }
    }
}