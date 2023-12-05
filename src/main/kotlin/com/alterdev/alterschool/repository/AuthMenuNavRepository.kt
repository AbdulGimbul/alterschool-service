package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthMenuNavigation
import org.springframework.data.jpa.repository.JpaRepository

interface AuthMenuNavRepository : JpaRepository<AuthMenuNavigation, String> {
}