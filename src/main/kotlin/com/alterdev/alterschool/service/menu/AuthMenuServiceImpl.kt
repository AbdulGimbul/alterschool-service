package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.config.ValidationUtil
import com.alterdev.alterschool.entity.AuthMenu
import com.alterdev.alterschool.entity.AuthUserRole
import com.alterdev.alterschool.model.request.AuthMenuReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.repository.AuthMenuRepository
import com.alterdev.alterschool.util.Helper
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date
import java.util.UUID
import java.util.stream.Collectors

@Service
class AuthMenuServiceImpl(
    private val authMenuRepository: AuthMenuRepository,
    private val validationUtil: ValidationUtil
) : AuthMenuService {

    override fun getAll(listRequest: ListRequest): List<AuthMenu> {
        val page = authMenuRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val authMenuList = page.get().collect(Collectors.toList())
        return authMenuList.map {
            AuthMenu(
                id = it.id,
                name = it.name,
                roles = it.roles.map { role ->
                    AuthUserRole(
                        id = role.id,
                        role = role.role,
                        url = role.url
                    )
                }.toSet(),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun getById(id: String): AuthMenu {
        val authMenu = findByIdOrThrowNotFound(id)
        return AuthMenu(
            id = authMenu.id,
            name = authMenu.name,
            roles = authMenu.roles.map { role ->
                AuthUserRole(
                    id = role.id,
                    role = role.role,
                    url = role.url
                )
            }.toSet(),
            createdAt = authMenu.createdAt,
            updatedAt = authMenu.updatedAt
        )
    }

    override fun create(authMenuReq: AuthMenuReq): AuthMenu {
        validationUtil.validate(authMenuReq)

        val authMenu = AuthMenu(
            id = Helper.generateUUIDWithTimestamp(),
            name = authMenuReq.name
        )
        authMenuRepository.save(authMenu)

        return authMenu
    }

    override fun update(id: String, authMenuReq: AuthMenuReq): AuthMenu {
        val authMenu = findByIdOrThrowNotFound(id)
        validationUtil.validate(authMenuReq)

        authMenu.apply {
            authMenuReq.name.let { name = it }
        }

        authMenuRepository.save(authMenu)

        return authMenu
    }

    override fun delete(id: String) {
        val authMenu = findByIdOrThrowNotFound(id)
        authMenuRepository.delete(authMenu)
    }

    private fun findByIdOrThrowNotFound(id: String): AuthMenu {
        return authMenuRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Menu with id $id not found")
    }
}