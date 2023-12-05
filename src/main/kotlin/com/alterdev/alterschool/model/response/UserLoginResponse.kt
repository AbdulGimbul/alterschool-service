package com.alterdev.alterschool.model.response

data class UserLoginResponse(
    val userId: String,
    val username: String,
    val role: String,
    val token: String
)
