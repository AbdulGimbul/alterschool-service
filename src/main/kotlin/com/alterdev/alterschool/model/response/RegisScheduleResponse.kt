package com.alterdev.alterschool.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class RegisScheduleResponse(

    val id: String,

    val periode: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val startTime: Date,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val endTime: Date,

    val status: Boolean,

    val image: String?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val createdAt: Date,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val updatedAt: Date?
)
