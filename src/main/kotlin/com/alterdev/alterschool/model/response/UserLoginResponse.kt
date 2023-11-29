package com.alterdev.alterschool.model.response

data class UserLoginResponse(
    val userId: Int,
    val username: String,
    val role: String,
    val token: String
)
