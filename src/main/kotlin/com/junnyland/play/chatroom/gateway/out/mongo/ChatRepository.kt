package com.junnyland.play.chatroom.gateway.out.mongo

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.mongo.ChatRoom.byDomain
import org.springframework.stereotype.Repository


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