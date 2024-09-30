package com.example.productmanagement.user.application

import com.example.productmanagement.user.domain.AccountDomainService
import com.example.productmanagement.user.domain.dto.SignUpDTO
import org.springframework.stereotype.Service

@Service
class AccountApplicationService: AccountDomainService {
    override fun signUp(): SignUpDTO {
        return SignUpDTO(
            userId = 123,
            refreshToken = "123-123",
        )
    }

    override fun logIn() {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }
}