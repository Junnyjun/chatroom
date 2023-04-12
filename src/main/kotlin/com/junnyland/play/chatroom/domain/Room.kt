package com.junnyland.play.chatroom.domain

data class Room(
    val id: String = "",
    val name: String,
    val description: String,
    val status:Status
){
    constructor(name: String, description: String) : this("", name, description, Status.OPEN)

    enum class Status {
        OPEN, CLOSE
    }
}

