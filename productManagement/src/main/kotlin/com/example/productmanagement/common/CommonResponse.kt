package com.example.productmanagement.common

import org.springframework.http.HttpStatus

data class CommonResponse(
    val meta: StatusCodeMeta,
    val data: Any? = null
) {
    data class StatusCodeMeta(
        private val httpStatus: HttpStatus,
        val code: Int = httpStatus.value(),
        val message: String = httpStatus.reasonPhrase,
    )
}
