package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "auth_user_role")
data class AuthUserRole(
    @Id
    @Column(name = "id_unique")
    val id: String,

    var role: String,
    var url: String,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "auth_menu_navigation",
        joinColumns = [JoinColumn(name = "id_role")],
        inverseJoinColumns = [JoinColumn(name = "id_menu")]
    )
    var menus: Set<AuthMenu> = HashSet(),

    @Column(name = "created_at")
    val createdAt: Date = Date(),

    @Column(name = "updated_at")
    val updatedAt: Date? = null
)
