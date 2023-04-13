package com.junnyland.play.chatroom.gateway.out.repository

import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatMessageMongoRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

interface ChatMessageRepository {
    fun findBy(roomName: String, pageable: Pageable): Any

    @Repository
    class ChatMessageNoSqlRepository(
        private val repository:ChatMessageMongoRepository
    ) : ChatMessageRepository{
        override fun findBy(roomName: String, pageable: Pageable): Any {
            return repository.findAllByRoomName(roomName)
                .map { e -> e.toDomain() }
        }

    }
}