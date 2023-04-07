package com.junnyland.play.chatroom.gateway.out.mongo;

import com.junnyland.play.chatroom.domain.Room;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatRoom")
public class ChatRoom {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    ChatRoom() {}

    private ChatRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ChatRoom byDomain(Room room) {
        return new ChatRoom(room.getName(), room.getDescription());
    }

    public Room toDomain() {
        return new Room(id.toString(),name, description);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}