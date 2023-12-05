package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "auth_menu_sub")
data class AuthMenuSub(
    @Id
    @Column(name = "id_unique")
    val id: String,

    var no: Int,
    var name: String,
    var icon: String,
    var url: String,

    @Column(name = "is_notif")
    var isNotif: Boolean,

    @Column(name = "is_hidden")
    var isHidden: Boolean,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu")
    var menu: AuthMenu,

    @Column(name = "created_at")
    val createdAt: Date = Date(),

    @Column(name = "updated_at")
    val updatedAt: Date? = null
)
