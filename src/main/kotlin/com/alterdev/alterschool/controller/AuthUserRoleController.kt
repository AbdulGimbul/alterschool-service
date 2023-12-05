package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.AuthUserRoleReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.service.menu.AuthUserRoleService
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user-role")
@Tag(name = "User Role")
class AuthUserRoleController(
    private val authUserRoleService: AuthUserRoleService
) {

    @Operation(
        summary = "Create user role",
    )
    @PostMapping
    fun createUserRole(
        @RequestBody body: AuthUserRoleReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authUserRoleService.create(body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get all user role"
    )
    @GetMapping
    fun getAllUserRole(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Map<String, Any?>> {
        val request = ListRequest(page, size)
        val response = authUserRoleService.getAll(request)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Get user role"
    )
    @GetMapping("/{id}")
    fun getUserRole(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        val response = authUserRoleService.getById(id)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Update user role by id"
    )
    @PutMapping("/{id}")
    fun updateUserRole(
        @PathVariable("id") id: String,
        @RequestBody body: AuthUserRoleReq
    ): ResponseEntity<Map<String, Any?>> {
        val response = authUserRoleService.update(id, body)

        return HttpResponse.setResp(data = response, message = "Success", status = HttpStatus.OK)
    }

    @Operation(
        summary = "Delete user role by id"
    )
    @DeleteMapping("/{id}")
    fun deleteUserRole(
        @PathVariable("id") id: String
    ): ResponseEntity<Map<String, Any?>> {
        authUserRoleService.delete(id)

        return HttpResponse.setResp<String>(message = "Success", status = HttpStatus.OK)
    }
}