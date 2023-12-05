package com.alterdev.alterschool.service.registerschedule

import com.alterdev.alterschool.model.request.RegisScheduleCreateReq
import com.alterdev.alterschool.model.request.RegisScheduleUpdateReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.RegisScheduleResponse
import org.springframework.core.io.InputStreamResource
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface RegisScheduleService {
    fun create(regisScheduleCreateReq: RegisScheduleCreateReq): RegisScheduleResponse

    fun get(id: String): RegisScheduleResponse

    fun update(id: String, regisScheduleUpdateReq: RegisScheduleUpdateReq): RegisScheduleResponse

    fun delete(id: String)

    fun getAll(listRequest: ListRequest): List<RegisScheduleResponse>

    fun uploadImage(id: String, imageFile: MultipartFile): RegisScheduleResponse

    fun downloadImage(id: String): ResponseEntity<InputStreamResource>
}