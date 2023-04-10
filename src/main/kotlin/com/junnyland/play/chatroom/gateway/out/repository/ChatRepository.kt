package com.junnyland.play.chatroom.gateway.out.repository

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.repository.ChatRoom.Companion.byDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


interface ChatRepository {
    fun save(room: Room): Room
    fun findAll(page: Pageable): Page<Room>
    fun find(name: String): Room

    @Repository
    class ChatNoSqlRepository(
        private val repository: ChatMongoRepository,
    ) : ChatRepository {

        override fun save(room: Room) = repository.existsByName(room.name)
            .takeUnless { it }
            ?.let { repository.save(byDomain(room)).toDomain() }
            ?: throw IllegalArgumentException("Room name already exists")

        override fun findAll(page: Pageable): Page<Room> = repository.findAll(page)
            .map { it.toDomain() }

        override fun find(name: String) = repository.findByName(name)
            ?.toDomain()
            ?: throw IllegalArgumentException("Room name not found")
    }


}