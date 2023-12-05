package com.alterdev.alterschool.model.response

import java.util.*

data class AuthMenuResponse(
    val id: Int,
    val name: String,
    val subs: List<AuthMenuSubResp>,
    val roles: List<AuthUserRoleResp>,
    val createdAt: Date,
    val updatedAt: Date?
)
