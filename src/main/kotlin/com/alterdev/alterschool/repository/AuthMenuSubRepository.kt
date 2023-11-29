package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthMenuSub
import org.springframework.data.jpa.repository.JpaRepository

interface AuthMenuSubRepository
    : JpaRepository<AuthMenuSub, Int>