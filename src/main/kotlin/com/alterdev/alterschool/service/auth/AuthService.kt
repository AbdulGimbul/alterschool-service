package com.alterdev.alterschool.service.auth

import com.alterdev.alterschool.model.request.LoginUserRequest
import com.alterdev.alterschool.model.response.UserLoginResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UserDetailsService

interface AuthService {

    fun login(loginUserRequest: LoginUserRequest, httpServletRequest: HttpServletRequest): UserLoginResponse
}