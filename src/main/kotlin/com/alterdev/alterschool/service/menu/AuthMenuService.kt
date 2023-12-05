package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.entity.AuthMenu
import com.alterdev.alterschool.model.request.AuthMenuReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.AuthMenuResponse

interface AuthMenuService {
    fun getAll(listRequest: ListRequest): List<AuthMenu>

    fun getById(id: String): AuthMenu

    fun create(authMenuReq: AuthMenuReq): AuthMenu

    fun update(id: String, authMenuReq: AuthMenuReq): AuthMenu

    fun delete(id: String)
}