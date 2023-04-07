package com.junnyland.play.chatroom.gateway.out

import com.junnyland.play.chatroom.domain.Room
import com.junnyland.play.chatroom.gateway.out.mongo.ChatMongoRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class ChatRepositoryTest {
    @Autowired
    lateinit var mongo: ChatMongoRepository

    lateinit var repository: ChatRepository

    @BeforeEach
    fun setup() {
        repository = ChatRepository.ChatNoSqlRepository(mongo)
    }

    @Test
    fun save(){
        assertThatCode {repository.save(Room("test", "test"))  }
            .doesNotThrowAnyException()
    }

    @AfterEach
    fun tearDown() {
        mongo.deleteAll()
    }
}