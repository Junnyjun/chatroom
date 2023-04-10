package com.junnyland.play.chatroom.gateway.`in`.http

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.repository.ChatRepository
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface RoomHelper {

    @ResponseBody
    @RestController
    @RequestMapping("/room")
    class RoomHttpHelper(
        private val repository: ChatRepository,
    ) : RoomHelper {
        @GetMapping
        fun getAll(page: Pageable) = repository.findAll(page)

        @GetMapping("/{name}")
        fun getOne(@PathVariable("name") name: String) = repository.find(name)

        @PostMapping
        fun save(@RequestBody room: Room) = repository.save(room)
    }
}