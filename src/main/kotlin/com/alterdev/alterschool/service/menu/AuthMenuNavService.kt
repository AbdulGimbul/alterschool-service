package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.model.request.AuthMenuNavReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.AuthMenuNavResponse

interface AuthMenuNavService {
    fun create(authMenuNavReq: AuthMenuNavReq): AuthMenuNavResponse

    fun getAllMenuNavigation(): List<AuthMenuNavResponse>

    fun getById(id: String): AuthMenuNavResponse

    fun update(id: String, menuNavReq: AuthMenuNavReq): AuthMenuNavResponse

    fun delete(id: String)
}