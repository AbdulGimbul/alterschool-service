package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthUserRole
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRoleRepository
    : JpaRepository<AuthUserRole, Int> {
}