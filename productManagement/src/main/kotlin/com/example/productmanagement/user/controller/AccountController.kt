package com.example.productmanagement.user.controller

import com.example.productmanagement.user.domain.AccountDomainService
import com.example.productmanagement.user.request.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class AccountController(
    private val accountDomainService: AccountDomainService,
) {

    // 회원 가입
    @PostMapping("/sign-up")
    fun signup(signUpRequest: SignUpRequest): ResponseEntity<String> {
        accountDomainService.signUp()
        return ResponseEntity.ok().build()
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