package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.AuthMenuSubReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.service.menu.AuthMenuSubService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu-sub")
@Tag(name = "Menu Sub")
class AuthMenuSubController(
    private val authMenuSubService: AuthMenuSubService
) {

    @Operation(
        summary = "Create menu sub",
    )
    @PostMapping
    fun createMenuSub(
        @RequestBody body: AuthMenuSubReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuSubService.create(body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get all menu sub"
    )
    @GetMapping
    fun getAllMenuSub(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any?>> {
        val request = ListRequest(page, size)
        val response = authMenuSubService.getAll(request)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Get menu sub"
    )
    @GetMapping("/{id}")
    fun getMenuSub(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuSubService.getById(id)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Update menu sub by id"
    )
    @PutMapping("/{id}")
    fun updateMenuSub(
        @PathVariable("id") id: String,
        @RequestBody body: AuthMenuSubReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuSubService.update(id, body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Delete menu sub by id"
    )
    @DeleteMapping("/{id}")
    fun deleteMenuSub(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        authMenuSubService.delete(id)

        return HttpResponse.setResp<String>(message = "Success", status = HttpStatus.OK)
    }
}