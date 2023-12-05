package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.AuthMenuReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.service.menu.AuthMenuService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menu")
class AuthMenuController(
    private val authMenuService: AuthMenuService
) {

    @Operation(
        summary = "Create menu",
    )
    @PostMapping
    fun createMenu(
        @RequestBody body: AuthMenuReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuService.create(body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get menu"
    )
    @GetMapping("/{id}")
    fun getMenu(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuService.getById(id)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Get all menu"
    )
    @GetMapping
    fun getAllMenu(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any?>> {
        val request = ListRequest(page, size)
        val response = authMenuService.getAll(request)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Update menu by id"
    )
    @PutMapping("/{id}")
    fun updateMenu(
        @PathVariable("id") id: String,
        @RequestBody body: AuthMenuReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authMenuService.update(id, body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Delete menu by id"
    )
    @DeleteMapping("/{id}")
    fun deleteMenu(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        authMenuService.delete(id)

        return HttpResponse.setResp<String>(message = "Success", status = HttpStatus.OK)
    }
}