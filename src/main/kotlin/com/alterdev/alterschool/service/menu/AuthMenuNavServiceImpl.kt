package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.entity.AuthMenuNavigation
import com.alterdev.alterschool.model.request.AuthMenuNavReq
import com.alterdev.alterschool.model.response.AuthMenuNavResponse
import com.alterdev.alterschool.repository.AuthMenuNavRepository
import com.alterdev.alterschool.repository.AuthMenuRepository
import com.alterdev.alterschool.repository.AuthUserRoleRepository
import com.alterdev.alterschool.util.Helper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthMenuNavServiceImpl(
    private val userRoleRepository: AuthUserRoleRepository,
    private val menuRepository: AuthMenuRepository,
    private val menuNavRepository: AuthMenuNavRepository,
) : AuthMenuNavService {
    override fun create(authMenuNavReq: AuthMenuNavReq): AuthMenuNavResponse {
        userRoleRepository.findByIdOrNull(authMenuNavReq.roleId)
            ?: throw IllegalArgumentException("Role not found")
        menuRepository.findByIdOrNull(authMenuNavReq.menuId)
            ?: throw IllegalArgumentException("Menu not found")

        val menuNavigation =
            AuthMenuNavigation(
                id = Helper.generateUUIDWithTimestamp(),
                idRole = authMenuNavReq.roleId,
                idMenu = authMenuNavReq.menuId
            )
        menuNavRepository.save(menuNavigation)

        return convertAuthMenuNavToAuthMenuNavResponse(menuNavigation)
    }

    override fun getAllMenuNavigation(): List<AuthMenuNavResponse> {
        val menuNavList = menuNavRepository.findAll()
        val menuNavResponseList = mutableListOf<AuthMenuNavResponse>()

        menuNavList.forEach { menuNav ->
            val menu = menuRepository.findByIdOrNull(menuNav.idMenu)
            val role = userRoleRepository.findByIdOrNull(menuNav.idRole)

            if (menu != null && role != null) {
                menuNavResponseList.add(
                    AuthMenuNavResponse(
                        id = menuNav.id,
                        idRole = role,
                        idMenu = menu,
                        createdAt = menuNav.createdAt,
                        updatedAt = menuNav.updatedAt
                    )
                )
            } else {
                throw IllegalArgumentException("Menu or Role not found")
            }
        }

        return menuNavResponseList
    }

    override fun getById(id: String): AuthMenuNavResponse {
        val menuNav = menuNavRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Menu Navigation not found")

        return convertAuthMenuNavToAuthMenuNavResponse(menuNav)
    }

    override fun update(id: String, menuNavReq: AuthMenuNavReq): AuthMenuNavResponse {
        val menuNavData = menuNavRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Menu Navigation not found")

        menuNavData.apply {
            menuNavReq.roleId.let { idRole = it }
            menuNavReq.menuId.let { idMenu = it }
        }

        menuNavRepository.save(menuNavData)

        return convertAuthMenuNavToAuthMenuNavResponse(menuNavData)
    }

    override fun delete(id: String) {
        val menuNav = menuNavRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Menu Navigation not found")

        menuNavRepository.delete(menuNav)
    }

    private fun convertAuthMenuNavToAuthMenuNavResponse(menuNav: AuthMenuNavigation): AuthMenuNavResponse {
        val menu = menuRepository.findById(menuNav.idMenu)
            .orElseThrow { RuntimeException("Menu not found for menu ID ${menuNav.idMenu}") }
        val role = userRoleRepository.findById(menuNav.idRole)
            .orElseThrow { RuntimeException("Role not found for role ID ${menuNav.idRole}") }

        return AuthMenuNavResponse(
            id = menuNav.id,
            idRole = role,
            idMenu = menu,
            createdAt = menuNav.createdAt,
            updatedAt = menuNav.updatedAt
        )
    }
}
