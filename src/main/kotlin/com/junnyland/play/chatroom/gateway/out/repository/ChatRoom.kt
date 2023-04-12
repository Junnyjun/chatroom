package com.junnyland.play.chatroom.gateway.out.repository

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.domain.Room.Status.OPEN
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chatRoom")
class ChatRoom(
    @Id private val id: String = "",
    @Indexed(unique = true) private val name: String,
    private var description: String = "",
    private val status: Room.Status = OPEN,
) {

    fun toDomain(): Room {
        return Room(id, name, description, status)
    }

    companion object {
        fun byDomain(room: Room): ChatRoom {
            return ChatRoom(room.name, room.description)
        }
    }
}