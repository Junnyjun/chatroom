package com.junnyland.play.chatroom.gateway.out.repository.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.stream.Stream

sealed interface ChatMessageMongoRepository : MongoRepository<ChatMessageEntity, ObjectId> {
    fun findAllByRoomName(roomName: String): Stream<ChatMessageEntity>
}