package com.alterdev.alterschool.service.menu

import com.alterdev.alterschool.config.ValidationUtil
import com.alterdev.alterschool.entity.AuthMenuSub
import com.alterdev.alterschool.model.request.AuthMenuSubReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.repository.AuthMenuRepository
import com.alterdev.alterschool.repository.AuthMenuSubRepository
import com.alterdev.alterschool.util.Helper
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthMenuSubServiceImpl(
    private val authMenuSubRepository: AuthMenuSubRepository,
    private val authMenuRepository: AuthMenuRepository,
    private val validationUtil: ValidationUtil
) : AuthMenuSubService {
    override fun getAll(listRequest: ListRequest): List<AuthMenuSub> {
        val page = authMenuSubRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val authMenuSubList = page.get().collect(Collectors.toList())
        return authMenuSubList.map {
            AuthMenuSub(
                id = it.id,
                name = it.name,
                no = it.no,
                icon = it.icon,
                url = it.url,
                isNotif = it.isNotif,
                isHidden = it.isHidden,
                menu = it.menu,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun getById(id: String): AuthMenuSub {
        val authMenuSub = findByIdOrThrowNotFound(id)
        return AuthMenuSub(
            id = authMenuSub.id,
            name = authMenuSub.name,
            no = authMenuSub.no,
            icon = authMenuSub.icon,
            url = authMenuSub.url,
            isNotif = authMenuSub.isNotif,
            isHidden = authMenuSub.isHidden,
            menu = authMenuSub.menu,
            createdAt = authMenuSub.createdAt,
            updatedAt = authMenuSub.updatedAt
        )
    }

    override fun create(authMenuSubRequest: AuthMenuSubReq): AuthMenuSub {
        validationUtil.validate(authMenuSubRequest)

        val menuId = authMenuSubRequest.menuId

        val authMenu = authMenuRepository.findByIdOrNull(menuId)
            ?: throw IllegalArgumentException("Auth menu with id $menuId not found")

        val authMenuSub = AuthMenuSub(
            id = Helper.generateUUIDWithTimestamp(),
            name = authMenuSubRequest.name,
            no = authMenuSubRequest.no,
            icon = authMenuSubRequest.icon,
            url = authMenuSubRequest.url,
            isNotif = authMenuSubRequest.isNotif,
            menu = authMenu,
            isHidden = authMenuSubRequest.isHidden
        )
        authMenuSubRepository.save(authMenuSub)

        return authMenuSub
    }

    override fun update(id: String, authMenuSubRequest: AuthMenuSubReq): AuthMenuSub {
        val authMenuSub = findByIdOrThrowNotFound(id)
        validationUtil.validate(authMenuSubRequest)

        val menuId = authMenuSubRequest.menuId

        val authMenu = authMenuRepository.findByIdOrNull(menuId)
            ?: throw IllegalArgumentException("Auth menu with id $menuId not found")

        authMenuSub.apply {
            authMenuSubRequest.name.let { name = it }
            authMenuSubRequest.no.let { no = it }
            authMenuSubRequest.icon.let { icon = it }
            authMenuSubRequest.url.let { url = it }
            authMenuSubRequest.isNotif.let { isNotif = it }
            authMenuSubRequest.isHidden.let { isHidden = it }
            authMenuSubRequest.menuId.let { menu = authMenu }
        }

        authMenuSubRepository.save(authMenuSub)

        return authMenuSub
    }

    override fun delete(id: String) {
        val authMenuSub = findByIdOrThrowNotFound(id)
        authMenuSubRepository.delete(authMenuSub)
    }

    private fun findByIdOrThrowNotFound(id: String): AuthMenuSub {
        return authMenuSubRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Menu with id $id not found")
    }
}