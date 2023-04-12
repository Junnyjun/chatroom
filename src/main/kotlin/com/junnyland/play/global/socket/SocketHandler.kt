package com.junnyland.play.global.socket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class SocketHandler : TextWebSocketHandler() {
    private val logger = LoggerFactory.getLogger("SocketHandler")

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info("afterConnectionEstablished: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("handleTextMessage: ${session.id}")
        logger.info("handleTextMessage: ${message.payload}")

        TextMessage("Hello, ${message.payload}").also {
            session.sendMessage(it)
        }
    }
}