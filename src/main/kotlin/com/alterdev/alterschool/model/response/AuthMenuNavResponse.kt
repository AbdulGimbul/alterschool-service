package com.alterdev.alterschool.model.response

import com.alterdev.alterschool.entity.AuthMenu
import com.alterdev.alterschool.entity.AuthUserRole
import java.util.Date

data class AuthMenuNavResponse(
    val id: String,
    val idRole: AuthUserRole?,
    val idMenu: AuthMenu?,
    val createdAt: Date,
    val updatedAt: Date?
)
