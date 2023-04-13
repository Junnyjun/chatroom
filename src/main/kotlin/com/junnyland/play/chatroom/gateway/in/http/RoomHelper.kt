package com.junnyland.play.chatroom.gateway.`in`.http

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.repository.ChatRoomRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

interface RoomHelper {

    @ResponseBody
    @RestController
    @RequestMapping("/room")
    class RoomHttpHelper(
        private val repository: ChatRoomRepository,
    ) : RoomHelper {
        @GetMapping
        fun getAll(@PageableDefault(size = 20, page = 0) page: Pageable) = repository.findAll(page)

        @GetMapping("/{name}")
        fun getOne(@PathVariable("name") name: String) = repository.find(name)

        @PostMapping
        fun save(@RequestBody room: RegisterRoom) = repository.save(room.toDomain)

        @DeleteMapping("/{name}")
        fun delete(@PathVariable("name") name: String) = repository.delete(name)
    }

    data class RegisterRoom(
        private val name: String? = "",
        private val description: String? = ""
    ){
        init {
            require(!name.isNullOrBlank()) { throw IllegalArgumentException("Room name is required") }
            require(!description.isNullOrBlank()) { throw IllegalArgumentException("Room description is required") }
        }
        val toDomain: Room get() = Room(
                name = name ?: "",
                description = description ?: ""
            )

    }
}