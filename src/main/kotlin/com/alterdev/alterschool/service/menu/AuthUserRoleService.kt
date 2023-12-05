package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.entity.AuthUserRole
import com.alterdev.alterschool.model.request.AuthUserRoleReq
import com.alterdev.alterschool.model.request.ListRequest

interface AuthUserRoleService {
    fun getAll(listRequest: ListRequest): List<AuthUserRole>

    fun getById(id: String): AuthUserRole

    fun create(authUserRoleRequest: AuthUserRoleReq): AuthUserRole

    fun update(id: String, authUserRoleRequest: AuthUserRoleReq): AuthUserRole

    fun delete(id: String)
}