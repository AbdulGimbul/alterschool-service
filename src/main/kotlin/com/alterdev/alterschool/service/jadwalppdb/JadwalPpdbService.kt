package com.alterdev.alterschool.service.jadwalppdb

import com.alterdev.alterschool.model.request.JadwalPpdbCreateReq
import com.alterdev.alterschool.model.request.JadwalPpdbUpdateReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.JadwalPpdbResponse

interface JadwalPpdbService {
    fun create(jadwalPpdbCreateReq: JadwalPpdbCreateReq): JadwalPpdbResponse

    fun get(id: Int): JadwalPpdbResponse

    fun update(id: Int, jadwalPpdbUpdateReq: JadwalPpdbUpdateReq): JadwalPpdbResponse

    fun delete(id: Int)

    fun getAll(ListRequest: ListRequest): List<JadwalPpdbResponse>
}