package com.junnyland.play.chatroom.gateway.out.repository

import com.junnyland.play.chatroom.domain.Message
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatMessageEntity
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatMessageEntity.Companion.byDomain
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatMessageMongoRepository
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface ChatMessageRepository {
    fun findBy(roomName: String, pageable: Pageable): Any
    fun saveAsync(message: Message)

    @Repository
    class ChatMessageNoSqlRepository(
        private val repository:ChatMessageMongoRepository,
    ) : ChatMessageRepository{
        override fun findBy(roomName: String, pageable: Pageable): Any {
            return repository.findAllByRoomName(roomName)
                .map { e -> e.toDomain() }
        }

        @Async
        @Transactional
        override fun saveAsync(message: Message) {
            repository.save(byDomain(message))
        }
    }
}