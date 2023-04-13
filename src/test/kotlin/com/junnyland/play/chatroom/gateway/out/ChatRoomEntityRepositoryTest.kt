package com.junnyland.play.chatroom.gateway.out

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.repository.mongo.ChatRoomMongoRepository
import com.junnyland.play.chatroom.gateway.out.repository.ChatRoomRepository
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@Disabled
@DataMongoTest
class ChatRoomEntityRepositoryTest {
    @Autowired
    lateinit var mongo: ChatRoomMongoRepository

    lateinit var repository: ChatRoomRepository

    @BeforeEach
    fun setup() {
        repository = ChatRoomRepository.ChatNoSqlRoomRepository(mongo)
    }

    @Test
    fun save() {
        assertThatCode { repository.save(Room("test", "test")) }
            .doesNotThrowAnyException()
    }

    @AfterEach
    fun tearDown() {
//        mongo.deleteAll()
    }
}