package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthUserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthUserRoleRepository
    : JpaRepository<AuthUserRole, String>{

        fun findAuthUserRolesByMenusId(id: String): List<AuthUserRole>
    }
