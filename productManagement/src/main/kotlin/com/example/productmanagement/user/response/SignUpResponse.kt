package com.example.productmanagement.user.response

import com.example.productmanagement.user.domain.dto.SignUpDTO

data class SignUpResponse(
    val userId: Long,
    val refreshToken: String,
)

fun SignUpDTO.toResponse() = SignUpResponse(userId = userId, refreshToken = refreshToken)