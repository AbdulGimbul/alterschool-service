package com.alterdev.alterschool.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.Date

class HttpResponse {
    companion object {
        fun <T> setResp(
            data: T? = null,
            message: String? = null,
            total: Int? = null,
            status: HttpStatus
        ): ResponseEntity<Map<String, Any?>> {
            val httpRes = mutableMapOf<String, Any?>()
            httpRes["timestamp"] = Date()
            httpRes["status"] = status.value()

            if (data != null) {
                httpRes["data"] = data
            }
            if (message != null) {
                httpRes["message"] = message
            }
            if (total != null) {
                httpRes["totalRow"] = total
            }

            return ResponseEntity.status(status.value()).body(httpRes)
        }
    }
}