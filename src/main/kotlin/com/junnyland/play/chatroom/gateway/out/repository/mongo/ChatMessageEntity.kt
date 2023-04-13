package com.junnyland.play.chatroom.gateway.out.repository.mongo

import com.junnyland.play.chatroom.domain.Message
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "chatMessage")
class ChatMessageEntity(
    @Id private val id: ObjectId = ObjectId.get(),
    @Indexed private val roomName: String,
    private val content: String,
    private val type:Message.MessageType,
    private val sender:String,
    @Indexed(direction = IndexDirection.DESCENDING) private val createdAt: String,
) {
    fun toDomain() {
        Message(
            roomName = roomName,
            message = content,
            type = type,
            sender = sender,
            timestamp = createdAt
        )
    }
    companion object{
        fun byDomain(message: Message) = ChatMessageEntity(
            roomName = message.roomName,
            content = message.message,
            type = message.type,
            sender = message.sender,
            createdAt = message.timestamp
        )
    }

}