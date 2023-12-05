package com.alterdev.alterschool.model.response

import java.util.*

data class AuthMenuSubResp(
    val id: Int,
    val no: Int,
    val name: String,
    val icon: String,
    val url: String,
    val isNotif: Boolean,
    val isHidden: Boolean,
    val menu: AuthMenuResponse,
    val createdAt: Date,
    val updatedAt: Date?
)
