package com.alterdev.alterschool.model.request

data class AuthUserRoleReq(
    val menuId: String,
    val role: String,
    val url: String
)