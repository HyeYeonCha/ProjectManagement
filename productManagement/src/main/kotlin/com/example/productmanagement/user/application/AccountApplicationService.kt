package com.example.productmanagement.user.application

import JwtUtil
import com.example.productmanagement.user.domain.AccountDomainService
import com.example.productmanagement.user.domain.UserRepository
import com.example.productmanagement.user.domain.dto.SignUpDTO
import org.springframework.stereotype.Service

@Service
class AccountApplicationService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
): AccountDomainService {
    override fun signUp(mobile: String, password: String): SignUpDTO {

        // mobile 로 user db search

        // 동일 유저 있다면 exception 처리

        // mobile 과 password 로 user 저장

        // userId 생성됨
        val userId = 123L

        // token 생성
        val jwt = jwtUtil.generateToken(userId = userId)

        // token 생성 후 db 저장

        // 반환
        return SignUpDTO(
            userId = userId,
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