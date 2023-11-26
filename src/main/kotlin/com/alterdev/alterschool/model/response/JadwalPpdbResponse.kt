package com.alterdev.alterschool.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class JadwalPpdbResponse(

    val id: Long,

    val periode: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val tglMulai: Date,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val tglSelesai: Date,

    val status: Boolean,

    val imgBrosur: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val createdAt: Date,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val updatedAt: Date?
)
