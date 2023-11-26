package com.alterdev.alterschool.repository

import com.alterdev.alterschool.entity.JadwalPpdb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JadwalPpdbRepository
    : JpaRepository<JadwalPpdb, Int>