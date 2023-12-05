package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*
import kotlin.collections.HashSet

@Entity
@Table(name = "auth_menu")
data class AuthMenu(
    @Id
    @Column(name = "id_unique")
    val id: String,
    var name: String,


    @ManyToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "menus"
    )
    val roles: Set<AuthUserRole> = HashSet(),

    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "updated_at")
    val updatedAt: Date? = null
)