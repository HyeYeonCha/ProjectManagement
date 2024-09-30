package com.example.productmanagement.user.domain

import com.example.productmanagement.user.domain.dto.SignUpDTO

interface AccountDomainService {
    fun signUp(mobile: String, password: String): SignUpDTO
    fun logIn()
    fun logOut()
}