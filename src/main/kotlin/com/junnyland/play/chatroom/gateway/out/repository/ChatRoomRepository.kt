package com.junnyland.play.chatroom.gateway.out.repository

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatRoomMongoRepository
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatRoomEntity.Companion.byDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


interface ChatRoomRepository {
    fun save(room: Room): Room
    fun findAll(page: Pageable): Page<Room>
    fun find(name: String): Room
    fun delete(name: String)

    @Repository
    class ChatNoSqlRoomRepository(
        private val repository: ChatRoomMongoRepository,
    ) : ChatRoomRepository {

        override fun save(room: Room) = repository.existsByName(room.name)
            .takeUnless { it }
            ?.let { repository.save(byDomain(room)).toDomain() }
            ?: throw IllegalArgumentException("Room name already exists")

        override fun findAll(page: Pageable): Page<Room> = repository.findAll(page)
            .map { it.toDomain() }

        override fun find(name: String) = repository.findByName(name)
            ?.toDomain()
            ?: throw IllegalArgumentException("Room name not found")

        override fun delete(name: String) = repository.deleteByName(name)

    }


}