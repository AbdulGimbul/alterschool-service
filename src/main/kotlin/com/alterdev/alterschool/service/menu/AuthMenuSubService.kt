package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.entity.AuthMenuSub
import com.alterdev.alterschool.model.request.AuthMenuSubReq
import com.alterdev.alterschool.model.request.ListRequest

interface AuthMenuSubService {
    fun getAll(listRequest: ListRequest): List<AuthMenuSub>

    fun getById(id: String): AuthMenuSub

    fun create(authMenuSubRequest: AuthMenuSubReq): AuthMenuSub

    fun update(id: String, authMenuSubRequest: AuthMenuSubReq): AuthMenuSub

    fun delete(id: String)
}