package com.alterdev.alterschool.service.jadwalppdb

import com.alterdev.alterschool.config.ValidationUtil
import com.alterdev.alterschool.entity.JadwalPpdb
import com.alterdev.alterschool.model.request.JadwalPpdbCreateReq
import com.alterdev.alterschool.model.request.JadwalPpdbUpdateReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.JadwalPpdbResponse
import com.alterdev.alterschool.repository.JadwalPpdbRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class JadwalPpdbServiceImpl(
    val jadwalPpdbRepository: JadwalPpdbRepository,
    val validationUtil: ValidationUtil
) : JadwalPpdbService {
    override fun create(jadwalPpdbCreateReq: JadwalPpdbCreateReq): JadwalPpdbResponse {
        validationUtil.validate(jadwalPpdbCreateReq)

        val jadwalPpdb = JadwalPpdb(
            periode = jadwalPpdbCreateReq.periode,
            tglMulai = jadwalPpdbCreateReq.tglMulai,
            tglSelesai = jadwalPpdbCreateReq.tglSelesai,
            status = jadwalPpdbCreateReq.status,
            imgBrosur = jadwalPpdbCreateReq.imgBrosur
        )

        jadwalPpdbRepository.save(jadwalPpdb)

        return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
    }

    override fun get(id: Int): JadwalPpdbResponse {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
    }

    override fun update(id: Int, jadwalPpdbUpdateReq: JadwalPpdbUpdateReq): JadwalPpdbResponse {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        validationUtil.validate(jadwalPpdbUpdateReq)

        jadwalPpdb.apply {
            jadwalPpdbUpdateReq.periode?.let { periode = it }
            jadwalPpdbUpdateReq.tglMulai?.let { tglMulai = it }
            jadwalPpdbUpdateReq.tglSelesai?.let { tglSelesai = it }
            jadwalPpdbUpdateReq.status?.let { status = it }
            jadwalPpdbUpdateReq.imgBrosur?.let { imgBrosur = it }
        }

        jadwalPpdbRepository.save(jadwalPpdb)

        return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
    }

    override fun delete(id: Int) {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        jadwalPpdbRepository.delete(jadwalPpdb)
    }

    override fun getAll(ListRequest: ListRequest): List<JadwalPpdbResponse> {
        val page = jadwalPpdbRepository.findAll(PageRequest.of(ListRequest.page, ListRequest.size))
        val jadwalPpdbList = page.get().collect(Collectors.toList())
        return jadwalPpdbList.map { convertJadwalPpdbToJadwalPpdbResponse(it) }
    }

    private fun findByIdOrThrowNotFound(id: Int): JadwalPpdb {
        return jadwalPpdbRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Jadwal PPDB with id $id not found")
    }

    private fun convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb: JadwalPpdb): JadwalPpdbResponse {
        return JadwalPpdbResponse(
            id = jadwalPpdb.id,
            periode = jadwalPpdb.periode,
            tglMulai = jadwalPpdb.tglMulai,
            tglSelesai = jadwalPpdb.tglSelesai,
            status = jadwalPpdb.status,
            imgBrosur = jadwalPpdb.imgBrosur,
            createdAt = jadwalPpdb.createdAt,
            updatedAt = jadwalPpdb.updatedAt
        )
    }
}