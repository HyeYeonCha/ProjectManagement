package com.example.productmanagement.user.controller

import com.example.productmanagement.common.CommonResponse
import com.example.productmanagement.user.domain.AccountDomainService
import com.example.productmanagement.user.request.SignUpRequest
import com.example.productmanagement.user.response.SignUpResponse
import com.example.productmanagement.user.response.toResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class AccountController (
    private val accountDomainService: AccountDomainService,
) {

    // 회원 가입
    @PostMapping("/sign-up")
    fun signup(signUpRequest: SignUpRequest): CommonResponse {
        val signupResponse: SignUpResponse =
            accountDomainService.signUp(mobile = signUpRequest.mobile, password = signUpRequest.password).toResponse()
        val metaResponse = CommonResponse.StatusCodeMeta(HttpStatus.CREATED)

        return CommonResponse(meta = metaResponse, data = signupResponse)
    }

    // 로그인
    @PostMapping("/login")
    fun login(): ResponseEntity<String> {
        accountDomainService.logIn()
        return ResponseEntity.ok().build()
    }

    // 로그아웃
    @DeleteMapping("/logout")
    fun logout(): ResponseEntity<String> {
        accountDomainService.logOut()
        return ResponseEntity.ok().build()
    }
}