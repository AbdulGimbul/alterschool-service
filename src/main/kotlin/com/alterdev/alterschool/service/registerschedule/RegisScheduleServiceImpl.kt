package com.alterdev.alterschool.service.registerschedule

import com.alterdev.alterschool.config.ValidationUtil
import com.alterdev.alterschool.entity.RegistrationSchedule
import com.alterdev.alterschool.model.request.RegisScheduleCreateReq
import com.alterdev.alterschool.model.request.RegisScheduleUpdateReq
import com.alterdev.alterschool.model.request.ListRequest
import com.alterdev.alterschool.model.response.RegisScheduleResponse
import com.alterdev.alterschool.repository.RegisScheduleRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

@Service
class RegisScheduleServiceImpl(
    val regisScheduleRepository: RegisScheduleRepository,
    val validationUtil: ValidationUtil
) : RegisScheduleService {

    @Value("\${file.upload-dir}")
    lateinit var uploadDir: String

    override fun create(regisScheduleCreateReq: RegisScheduleCreateReq): RegisScheduleResponse {
        validationUtil.validate(regisScheduleCreateReq)

        val registrationSchedule = RegistrationSchedule(
            id = UUID.randomUUID().toString(),
            periode = regisScheduleCreateReq.periode,
            startTime = regisScheduleCreateReq.tglMulai,
            endTime = regisScheduleCreateReq.tglSelesai,
            status = regisScheduleCreateReq.status
        )

        regisScheduleRepository.save(registrationSchedule)

        return convertJadwalPpdbToJadwalPpdbResponse(registrationSchedule)
    }

    override fun get(id: Int): RegisScheduleResponse {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
    }

    override fun update(id: Int, regisScheduleUpdateReq: RegisScheduleUpdateReq): RegisScheduleResponse {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        validationUtil.validate(regisScheduleUpdateReq)

        jadwalPpdb.apply {
            regisScheduleUpdateReq.periode?.let { periode = it }
            regisScheduleUpdateReq.tglMulai?.let { startTime = it }
            regisScheduleUpdateReq.tglSelesai?.let { endTime = it }
            regisScheduleUpdateReq.status?.let { status = it }
            regisScheduleUpdateReq.imgBrosur?.let { image = it }
        }

        regisScheduleRepository.save(jadwalPpdb)

        return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
    }

    override fun delete(id: Int) {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        regisScheduleRepository.delete(jadwalPpdb)
    }

    override fun getAll(ListRequest: ListRequest): List<RegisScheduleResponse> {
        val page = regisScheduleRepository.findAll(PageRequest.of(ListRequest.page, ListRequest.size))
        val jadwalPpdbList = page.get().collect(Collectors.toList())
        return jadwalPpdbList.map { convertJadwalPpdbToJadwalPpdbResponse(it) }
    }

    override fun uploadImage(id: Int, imageFile: MultipartFile): RegisScheduleResponse {
        if (imageFile.isEmpty) {
            throw IllegalArgumentException("Image file is required")
        }

        val uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.originalFilename

        try {
            val uploadPath: Path = Paths.get(uploadDir)

            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs()
            }

            val filePath = uploadPath.resolve(uniqueFileName)
            Files.copy(imageFile.inputStream, filePath)

            val fullImagePath = uploadPath.resolve(uniqueFileName).toString()

            val jadwalPpdb = findByIdOrThrowNotFound(id)
            jadwalPpdb.image = fullImagePath
            regisScheduleRepository.save(jadwalPpdb)

            return convertJadwalPpdbToJadwalPpdbResponse(jadwalPpdb)
        } catch (e: Exception) {
            throw IllegalArgumentException("Failed to upload image file : ${e.message}")
        }
    }

    @Throws
    override fun downloadImage(id: Int): ResponseEntity<InputStreamResource> {
        val jadwalPpdb = findByIdOrThrowNotFound(id)
        val filePath = jadwalPpdb.image

        val file = Paths.get(filePath).toFile()
        val resource = InputStreamResource(file.inputStream())

        val contentType =
            when {
                file.name.endsWith("png") -> MediaType.IMAGE_PNG
                file.name.endsWith("jpg") || file.name.endsWith("jpeg") -> MediaType.IMAGE_JPEG
                else -> throw IllegalArgumentException("File format not supported")
            }

        return ResponseEntity.ok()
            .contentLength(file.length())
            .contentType(contentType)
            .body(resource)
    }

    private fun findByIdOrThrowNotFound(id: Int): RegistrationSchedule {
        return regisScheduleRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Jadwal PPDB with id $id not found")
    }

    private fun convertJadwalPpdbToJadwalPpdbResponse(registrationSchedule: RegistrationSchedule): RegisScheduleResponse {
        return RegisScheduleResponse(
            id = registrationSchedule.id,
            periode = registrationSchedule.periode,
            startTime = registrationSchedule.startTime,
            endTime = registrationSchedule.endTime,
            status = registrationSchedule.status,
            image = registrationSchedule.image,
            createdAt = registrationSchedule.createdAt,
            updatedAt = registrationSchedule.updatedAt
        )
    }
}