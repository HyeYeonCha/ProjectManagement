package com.example.productmanagement.user.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class AccountController {

    // 회원 가입
    @PostMapping("/sign-up")
    fun signup() {}

    // 로그인
    @PostMapping("/login")
    fun login() {}

    // 로그아웃
    @DeleteMapping("/logout")
    fun logout() {}
}