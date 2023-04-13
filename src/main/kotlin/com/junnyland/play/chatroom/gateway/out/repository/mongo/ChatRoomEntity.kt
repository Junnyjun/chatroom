package com.junnyland.play.chatroom.gateway.out.repository.mongo

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.domain.Room.Status.OPEN
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chatRoom")
class ChatRoomEntity(
    @Id private val id: ObjectId = ObjectId.get(),
    @Indexed(unique = true) private val name: String,
    private val description: String = "",
    private val status: Room.Status = OPEN,
    @Indexed(direction = IndexDirection.DESCENDING) private val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    fun toDomain(): Room {
        return Room(id.toString(), name, description, status)
    }

    companion object {
        fun byDomain(room: Room): ChatRoomEntity {
            return ChatRoomEntity(
                name = room.name,
                description = room.description,
                status = room.status
            )
        }
    }
}