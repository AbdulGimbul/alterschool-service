package com.alterdev.alterschool.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "auth_menu_navigation")
data class AuthMenuNavigation(
    @Id
    @Column(name = "id_unique")
    val id: String,

    @Column(name = "id_role")
    var idRole: String,

    @Column(name = "id_menu")
    var idMenu: String,

    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "updated_at")
    val updatedAt: Date? = null
)