package com.junnyland.play.global.async

import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@EnableAsync
@Configuration
class AsyncConfig : AsyncConfigurer {
    val log = LoggerFactory.getLogger("async")

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler =
        AsyncUncaughtExceptionHandler { throwable, method, any ->
            log.error("Exception message - " + throwable.message)
            log.error("Method name - " + method.name)
            any.forEach { log.error("Parameter value - $it") }
        }


    override fun getAsyncExecutor(): Executor = ThreadPoolTaskExecutor()
        .also {
            it.corePoolSize = 5
            it.maxPoolSize = 200
            it.threadNamePrefix = "junnyland-executor"
            it.initialize()
        }
}
