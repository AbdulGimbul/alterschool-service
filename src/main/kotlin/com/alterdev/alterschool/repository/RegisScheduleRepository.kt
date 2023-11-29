package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.RegistrationSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegisScheduleRepository
    : JpaRepository<RegistrationSchedule, Int>