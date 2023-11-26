package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.JadwalPpdbCreateReq
import com.alterdev.alterschool.model.request.JadwalPpdbUpdateReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.service.jadwalppdb.JadwalPpdbService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/jadwal-ppdb")
@Tag(name = "Jadwal PPDB")
class JadwalPpdbController(val jadwalPpdbService: JadwalPpdbService) {

    @Operation(
        summary = "Create jadwal ppdb",
    )
    @PostMapping
    fun createJadwalPpdb(
        @RequestBody body: JadwalPpdbCreateReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = jadwalPpdbService.create(body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get jadwal ppdb",
    )
    @GetMapping("/{id}")
    fun getJadwalPpdb(@PathVariable("id") id: Int): ResponseEntity<Map<String, Any?>> {
        val response = jadwalPpdbService.get(id)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)

    }

    @Operation(
        summary = "Get all jadwal ppdb",
    )
    @GetMapping
    fun getAllJadwalPpdb(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any?>> {
        val request = ListRequest(page, size)
        val response = jadwalPpdbService.getAll(request)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Update jadwal ppdb",
    )
    @PutMapping("/{id}")
    fun updateJadwalPpdb(
        @PathVariable("id") id: Int,
        @RequestBody body: JadwalPpdbUpdateReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = jadwalPpdbService.update(id, body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Delete jadwal ppdb",
    )
    @DeleteMapping("/{id}")
    fun deleteJadwalPpdb(@PathVariable("id") id: Int): ResponseEntity<Map<String, Any?>> {
        jadwalPpdbService.delete(id)

        return HttpResponse.setResp<String>(message = "Success", status = HttpStatus.OK)
    }
}