package com.junnyland.play.chatroom.gateway.`in`.http

import com.junnyland.play.chatroom.gateway.out.repository.ChatMessageRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface MessageHelper {
    @ResponseBody
    @RestController
    @RequestMapping("/message")
    class MessageHttpHelper(
        private val repository: ChatMessageRepository,
    ) : MessageHelper {

        @GetMapping
        fun getMessage(@PathVariable("roomName") messageFacets: MessageFacets) = repository
            .findBy(messageFacets.name,messageFacets.pageable)

    }

    data class MessageFacets(
        private val roomName: String,
        private val page: Int = 0,
        private val size: Int = 30,
    ){
        val pageable: Pageable get() = PageRequest.of(page, size)
        val name: String get() = roomName
    }
}