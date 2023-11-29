package com.alterdev.alterschool.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class RegisScheduleUpdateReq(
    val periode: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val tglMulai: Date? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val tglSelesai: Date? = null,

    val status: Boolean? = null,

    val imgBrosur: String? = null
)