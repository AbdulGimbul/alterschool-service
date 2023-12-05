package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.config.ValidationUtil
import com.alterdev.alterschool.entity.AuthMenu
import com.alterdev.alterschool.entity.AuthUserRole
import com.alterdev.alterschool.model.request.AuthUserRoleReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.repository.AuthMenuRepository
import com.alterdev.alterschool.repository.AuthUserRoleRepository
import com.alterdev.alterschool.util.Helper
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
@Slf4j
class AuthUserRoleServiceImpl(
    private val authUserRoleRepository: AuthUserRoleRepository,
    private val validationUtil: ValidationUtil
) : AuthUserRoleService {
    val loggerFactory = LoggerFactory.getLogger(AuthUserRoleServiceImpl::class.java)

    override fun getAll(listRequest: ListRequest): List<AuthUserRole> {
        val page = authUserRoleRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val authUserRoleList = page.get().collect(Collectors.toList())

        return authUserRoleList.map {
            AuthUserRole(
                id = it.id,
                role = it.role,
                url = it.url,
                menus = it.menus.map { menu ->
                    AuthMenu(
                        id = menu.id,
                        name = menu.name
                    )
                }.toSet(),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun getById(id: String): AuthUserRole {
        val authUserRole = findByIdOrThrowNotFound(id)

        return AuthUserRole(
            id = authUserRole.id,
            role = authUserRole.role,
            url = authUserRole.url,
            menus = authUserRole.menus.map { menu ->
                AuthMenu(
                    id = menu.id,
                    name = menu.name
                )
            }.toSet(),
            createdAt = authUserRole.createdAt,
            updatedAt = authUserRole.updatedAt
        )
    }

    override fun create(authUserRoleRequest: AuthUserRoleReq): AuthUserRole {
        validationUtil.validate(authUserRoleRequest)

        val authUserRole = AuthUserRole(
            id = Helper.generateUUIDWithTimestamp(),
            role = authUserRoleRequest.role,
            url = authUserRoleRequest.url
        )

        authUserRoleRepository.save(authUserRole)

        return authUserRole
    }

    override fun update(id: String, authUserRoleRequest: AuthUserRoleReq): AuthUserRole {
        val authUserRole = findByIdOrThrowNotFound(id)
        validationUtil.validate(authUserRoleRequest)

        authUserRole.apply {
            authUserRoleRequest.role.let { role = it }
            authUserRoleRequest.url.let { url = it }
        }

        authUserRoleRepository.save(authUserRole)

        return authUserRole
    }

    override fun delete(id: String) {
        val authUserRole = findByIdOrThrowNotFound(id)
        authUserRoleRepository.delete(authUserRole)
    }

    private fun findByIdOrThrowNotFound(id: String): AuthUserRole {
        return authUserRoleRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Auth user role with id $id not found")
    }
}