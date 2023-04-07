package com.junnyland.play.chatroom.gateway.out.mongo

import org.springframework.data.mongodb.repository.MongoRepository

sealed interface ChatMongoRepository : MongoRepository<ChatRoom, String> {
    fun findByName(name: String): ChatRoom
    fun existsByName(name: String): Boolean
}