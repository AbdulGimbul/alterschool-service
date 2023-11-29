package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AuthUserRepository
    : JpaRepository<AuthUser, Int>{

        fun findBy_username(username: String): AuthUser?

        fun findFirstBy_username(username: String): AuthUser?
}