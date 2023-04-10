package com.junnyland.play.config.mongo

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(basePackages = ["com.junnyland.play"],)
@EnableMongoAuditing
class MongodbConfig {

    @Bean
    fun transactionManager(dbFactory: MongoDatabaseFactory): MongoTransactionManager =
        MongoTransactionManager(dbFactory)



    @Bean
    fun mongoTemplate(mongoClient: MongoClient): MongoTemplate {
        val factory: MongoDatabaseFactory = SimpleMongoClientDatabaseFactory(mongoClient, "blog")
        return MongoTemplate(factory)
    }
}