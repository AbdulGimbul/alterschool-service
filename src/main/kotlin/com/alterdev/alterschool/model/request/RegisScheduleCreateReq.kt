package com.alterdev.alterschool.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotBlank
import java.util.*

data class RegisScheduleCreateReq(
    @NotBlank(message = "Periode tidak boleh kosong")
    val periode: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotBlank(message = "Tanggal mulai tidak boleh kosong")
    val tglMulai: Date,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotBlank(message = "Tanggal selesai tidak boleh kosong")
    val tglSelesai: Date,

    @NotBlank(message = "Status tidak boleh kosong")
    val status: Boolean,
)
