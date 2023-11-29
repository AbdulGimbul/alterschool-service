package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthMenuRepository
    : JpaRepository<AuthMenu, Int> {
}