package com.madadipouya.redis.springdata.example.controller.exception.handler

import com.madadipouya.redis.springdata.example.service.exception.ActorNotFoundException
import com.madadipouya.redis.springdata.example.service.exception.MovieNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(value = [ActorNotFoundException::class, MovieNotFoundException::class])
    fun handleNotFoundExceptions(exception: Exception, webRequest: WebRequest): ResponseEntity<ApiError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError(exception.message.orEmpty()))
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationExceptions(
        exception: ConstraintViolationException,
        webRequest: WebRequest
    ): ResponseEntity<ApiError> {
        val errors = exception.constraintViolations.map { violation -> violation.message }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError(errors))
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentValidationExceptions(
        exception: MethodArgumentNotValidException,
        webRequest: WebRequest
    ): ResponseEntity<ApiError> {
        val errors = exception.bindingResult.fieldErrors.map { fieldError ->
            "${fieldError.field}: ${fieldError.defaultMessage}"
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError(errors))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleOtherExceptions(exception: Exception, webRequest: WebRequest): ResponseEntity<ApiError>? {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiError("Server encountered an error"))
    }

    data class ApiError(val errors: List<String>) {
        constructor(error: String) : this(listOf(error))
    }
}