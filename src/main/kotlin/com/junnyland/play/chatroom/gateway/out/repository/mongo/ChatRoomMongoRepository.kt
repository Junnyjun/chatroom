package com.junnyland.play.chatroom.gateway.out.repository.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

sealed interface ChatRoomMongoRepository : MongoRepository<ChatRoomEntity, ObjectId> {
    fun findByName(name: String): ChatRoomEntity?
    fun existsByName(name: String): Boolean
    fun deleteByName(name: String)
}