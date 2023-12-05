package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.request.RegisScheduleCreateReq
import com.alterdev.alterschool.model.request.RegisScheduleUpdateReq
import com.alterdev.alterschool.service.registerschedule.RegisScheduleService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/jadwal-ppdb")
@Tag(name = "Jadwal PPDB")
class RegisScheduleController(val regisScheduleService: RegisScheduleService) {

    @Operation(
        summary = "Create jadwal ppdb",
    )
    @PostMapping
    fun createJadwalPpdb(
        @RequestBody body: RegisScheduleCreateReq,
    ): ResponseEntity<Map<String, Any?>> {
        val response = regisScheduleService.create(body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get jadwal ppdb",
    )
    @GetMapping("/{id}")
    fun getJadwalPpdb(@PathVariable("id") id: String): ResponseEntity<Map<String, Any?>> {
        val response = regisScheduleService.get(id)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)

    }

    @Operation(
        summary = "Get all jadwal ppdb",
    )
    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    fun getAllJadwalPpdb(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any?>> {
        val request = ListRequest(page, size)
        val response = regisScheduleService.getAll(request)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Update jadwal ppdb",
    )
    @PutMapping("/{id}")
    fun updateJadwalPpdb(
        @PathVariable("id") id: String,
        @RequestBody body: RegisScheduleUpdateReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = regisScheduleService.update(id, body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Delete jadwal ppdb",
    )
    @DeleteMapping("/{id}")
    fun deleteJadwalPpdb(@PathVariable("id") id: String): ResponseEntity<Map<String, Any?>> {
        regisScheduleService.delete(id)

        return HttpResponse.setResp<String>(message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Upload image jadwal ppdb",
    )
    @PostMapping("/{id}/upload-image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadImageJadwalPpdb(
        @PathVariable("id") id: String,
        @RequestParam("image") imageFile: MultipartFile
    ): ResponseEntity<Map<String, Any?>> {
        val response = regisScheduleService.uploadImage(id, imageFile)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Download image jadwal ppdb",
    )
    @GetMapping("/{id}/download-image")
    fun downloadImageJadwalPpdb(@PathVariable("id") id: String): ResponseEntity<InputStreamResource> {

        return regisScheduleService.downloadImage(id)
    }
}