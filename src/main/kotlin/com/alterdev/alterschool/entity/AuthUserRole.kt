package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "auth_user_role")
data class AuthUserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_unique")
    val id: Int,
    val role: String,
    val url: String,
    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    val users: List<AuthUser>,
    @ManyToMany(cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    @JoinTable(name = "auth_menu_navigation",
        joinColumns = [JoinColumn(name = "id_role")],
        inverseJoinColumns = [JoinColumn(name = "id_menu")])
    val menus: List<AuthMenu>,
    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "modified_at")
    val updatedAt: Date? = null
)
