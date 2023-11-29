package com.alterdev.alterschool.advice

import com.alterdev.alterschool.util.HttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<Map<String, Any?>> {
        return HttpResponse.setResp<String>(
            message = e.message ?: "Something went wrong",
            status = HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(e: UsernameNotFoundException): ResponseEntity<Map<String, Any?>> {
        return HttpResponse.setResp<String>(message = e.message ?: "User not found", status = HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<Map<String, Any?>> {
        return HttpResponse.setResp<String>(message = e.message ?: "Invalid argument", status = HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Map<String, Any?>> {
        val errors = e.bindingResult.fieldErrors.associate {
            it.field to it.defaultMessage
        }
        return HttpResponse.setResp(data = errors, message = "Validation failed", status = HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<Map<String, Any?>> {
        return HttpResponse.setResp<String>(
            message = "Http request not supported",
            status = HttpStatus.METHOD_NOT_ALLOWED
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<Map<String, Any?>> {
        return HttpResponse.setResp<String>(message = "Endpoint not found", status = HttpStatus.NOT_FOUND)
    }
}