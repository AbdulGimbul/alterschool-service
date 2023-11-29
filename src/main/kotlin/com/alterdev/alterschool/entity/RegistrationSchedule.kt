package com.alterdev.alterschool.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "registration_schedule")
data class RegistrationSchedule(

    @Id
    @Column(name = "id_unique", unique = true)
    val id: String,

    var periode: String,

    @Column(name = "start_time")
    var startTime: Date,

    @Column(name = "end_time")
    var endTime: Date,

    var status: Boolean,

    var image: String? = null,

    @Column(name = "created_at")
    val createdAt: Date = Date(),

    @Column(name = "updated_at")
    val updatedAt: Date? = null
)
