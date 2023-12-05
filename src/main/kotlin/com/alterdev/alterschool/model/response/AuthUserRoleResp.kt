package com.alterdev.alterschool.model.response

import java.util.*

data class AuthUserRoleResp(
    val id: Int,
    val role: String,
    val url: String,
    val users: List<UserLoginResponse>,
    val menus: List<AuthMenuResponse>,
    val createdAt: Date,
    val updatedAt: Date?
)
