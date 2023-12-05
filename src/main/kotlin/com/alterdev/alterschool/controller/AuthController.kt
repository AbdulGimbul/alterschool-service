package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.LoginUserRequest
import com.alterdev.alterschool.service.auth.AuthService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping(
        path = ["/login"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun loginUser(
        @RequestBody loginUserRequest: LoginUserRequest,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Map<String, Any?>> {
        val response = authService.login(loginUserRequest, httpServletRequest)
        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }
}