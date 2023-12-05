package com.alterdev.alterschool.service.auth

import com.alterdev.alterschool.config.JwtService
import com.alterdev.alterschool.model.request.LoginUserRequest
import com.alterdev.alterschool.model.response.UserLoginResponse
import com.alterdev.alterschool.repository.AuthUserRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val authUserRepository: AuthUserRepository,
    private val authManager: AuthenticationManager,
    private val jwtService: JwtService

) : AuthService {
    override fun login(loginUserRequest: LoginUserRequest, httpServletRequest: HttpServletRequest): UserLoginResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginUserRequest.username,
                loginUserRequest.password
            )
        )

        val userByUsername = authUserRepository.findFirstBy_username(loginUserRequest.username)
            ?: throw UsernameNotFoundException("User Not Found")

        val jwtToken = jwtService.generateToken(userByUsername)

        return UserLoginResponse(
            userId = userByUsername.id,
            username = userByUsername._username,
            role = userByUsername.role.role,
            token = jwtToken
        )
    }
}