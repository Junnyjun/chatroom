package com.junnyland.play.chatroom.domain

data class Room(
    val id: String,
    val name: String,
    val description: String,
){
    constructor(name: String, description: String) : this("", name, description)
}
