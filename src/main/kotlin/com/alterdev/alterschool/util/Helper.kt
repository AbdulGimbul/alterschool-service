package com.alterdev.alterschool.util

import java.util.*

object Helper {
    fun generateUUIDWithTimestamp(): String {
        val uuid = UUID.randomUUID()
        val timestamp = System.currentTimeMillis()

        // Extract the most significant bits from the UUID and combine them with the timestamp
        val combinedUuid = UUID(uuid.mostSignificantBits, timestamp)
        return combinedUuid.toString()
    }
}