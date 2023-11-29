package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "auth_menu")
data class AuthMenu(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_unique")
    val id: Int,
    val name: String,
    @OneToMany(mappedBy = "menu", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    val subs: List<AuthMenuSub>,
    @ManyToMany(mappedBy = "menus", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    val roles: List<AuthUserRole>,
    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "modified_at")
    val updatedAt: Date? = null
)
