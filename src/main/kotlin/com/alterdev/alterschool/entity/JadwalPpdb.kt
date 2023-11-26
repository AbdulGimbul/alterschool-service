package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "jadwal_ppdb")
data class JadwalPpdb(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    var periode: String,

    @Column(name = "tgl_mulai")
    var tglMulai: Date,

    @Column(name = "tgl_selesai")
    var tglSelesai: Date,

    var status: Boolean,

    @Column(name = "img_brosur")
    var imgBrosur: String,

    @Column(name = "created_at")
    val createdAt: Date = Date(),

    @Column(name = "updated_at")
    val updatedAt: Date? = null
)
