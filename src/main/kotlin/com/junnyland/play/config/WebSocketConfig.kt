package com.junnyland.play.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.handler.TextWebSocketHandler

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketConfigurer {



    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SocketHandler(), "/ws/chat")
            .setAllowedOrigins("*")
            .withSockJS()
    }

    @Component
    class SocketHandler: TextWebSocketHandler(){
        override fun afterConnectionEstablished(session: WebSocketSession) {
            super.afterConnectionEstablished(session)
        }

        override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
            super.afterConnectionClosed(session, status)
        }

        override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
            super.handleTextMessage(session, message)
        }
    }
}