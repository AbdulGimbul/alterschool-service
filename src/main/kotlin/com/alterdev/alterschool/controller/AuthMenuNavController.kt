package com.alterdev.alterschool.controller

import com.alterdev.alterschool.model.request.AuthMenuNavReq
import com.alterdev.alterschool.service.menu.AuthMenuNavServiceImpl
import com.alterdev.alterschool.util.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menu-nav")
@Tag(name = "Menu Navigation")
class AuthMenuNavController(
    private val authMenuNavService: AuthMenuNavServiceImpl
) {

    @Operation(
        summary = "Associate role with menu"
    )
    @PostMapping
    fun create(@RequestBody authMenuNavReq: AuthMenuNavReq):
            ResponseEntity<Map<String, Any?>> {
        val isAssociated = authMenuNavService.create(authMenuNavReq)

        return HttpResponse.setResp(
            message = "Menu associated with role",
            data = isAssociated,
            status = HttpStatus.CREATED
        )
    }

    @Operation(
        summary = "Get all menu navigation"
    )
    @GetMapping
    fun getAllMenuNavigation(): ResponseEntity<Map<String, Any?>> {
        val response = authMenuNavService.getAllMenuNavigation()

        return HttpResponse.setResp(
            message = "Success",
            data = response,
            status = HttpStatus.OK
        )
    }

    @Operation(
        summary = "Get menu navigation by id"
    )
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Map<String, Any?>> {
        val response = authMenuNavService.getById(id)

        return HttpResponse.setResp(
            message = "Success",
            data = response,
            status = HttpStatus.OK
        )
    }

    @Operation(
        summary = "Update menu navigation by id"
    )
    @PutMapping("/{id}")
    fun update(@PathVariable id: String,
               @RequestBody authMenuNavReq: AuthMenuNavReq):
            ResponseEntity<Map<String, Any?>> {
        val response = authMenuNavService.update(id, authMenuNavReq)

        return HttpResponse.setResp(
            message = "Menu navigation updated",
            data = response,
            status = HttpStatus.OK
        )
    }

    @Operation(
        summary = "Delete menu navigation by id"
    )
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Map<String, Any?>> {
        authMenuNavService.delete(id)

        return HttpResponse.setResp<String>(
            message = "Menu navigation deleted",
            status = HttpStatus.OK
        )
    }
}