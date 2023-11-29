package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "auth_menu_sub")
data class AuthMenuSub(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_unique")
    val id: Int,
    val no: Int,
    val name: String,
    val icon: String,
    val url: String,
    @Column(name = "is_notif")
    val isNotif: Boolean,
    @Column(name = "is_hidden")
    val isHidden: Boolean,
    @ManyToOne(cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    @JoinColumn(name = "id_menu")
    val menu: AuthMenu,
    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "modified_at")
    val updatedAt: Date? = null
)
