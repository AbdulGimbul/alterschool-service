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

    fun get(id: Int): RegisScheduleResponse

    fun update(id: Int, regisScheduleUpdateReq: RegisScheduleUpdateReq): RegisScheduleResponse

    fun delete(id: Int)

    fun getAll(ListRequest: ListRequest): List<RegisScheduleResponse>

    fun uploadImage(id: Int, imageFile: MultipartFile): RegisScheduleResponse

    fun downloadImage(id: Int): ResponseEntity<InputStreamResource>
}