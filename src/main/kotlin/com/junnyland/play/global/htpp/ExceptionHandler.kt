package com.junnyland.play.global.htpp

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ExceptionResponse> =
        status(BAD_REQUEST)
            .body(ExceptionResponse(400, e.message ?: "Bad Request"))


    class ExceptionResponse(
        val status: Int,
        val message: String,
    )
}