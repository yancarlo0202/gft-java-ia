package me.dio.controller.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBusinessException(businessException: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(businessException.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFoundException(notFoundException: NoSuchElementException?): ResponseEntity<String> {
        return ResponseEntity("Resource ID not found.", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Throwable::class)
    fun handleUnexpectedException(unexpectedException: Throwable?): ResponseEntity<String> {
        val message = "Unexpected server error, see the logs."
        logger.error(message, unexpectedException)
        return ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}