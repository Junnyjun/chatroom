package com.junnyland.play.chatroom.gateway.out

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.mongo.ChatMongoRepository
import com.junnyland.play.chatroom.gateway.out.mongo.ChatRoom.byDomain
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional


interface ChatRepository {
    fun save(room: Room): Room

    @Repository
    class ChatNoSqlRepository(
        private val repository: ChatMongoRepository,
    ) : ChatRepository {

        override fun save(room: Room) = repository.existsByName(room.name)
                .takeUnless { it }
                ?.let { repository.save(byDomain(room)).toDomain() }
                ?: throw IllegalArgumentException("Room name already exists")
    }
}