package com.alterdev.alterschool.model.request

data class AuthMenuSubReq(
    val menuId: String,
    val no: Int,
    val name: String,
    val icon: String,
    val url: String,
    val isNotif: Boolean,
    val query: String,
    val isHidden: Boolean
)
