package com.example.productmanagement.common

import JwtUtil
import com.example.productmanagement.user.persistence.JpaUserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@Component
class JwtRequestFilter(
    private val jwtUtil: JwtUtil,
    private val jpaUserRepository: JpaUserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        var jwt: String? = null
        var userId: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7)
            userId = jwtUtil.extractUserId(jwt)
        }

        if (userId != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = jpaUserRepository.findById(userId.toLong())

            if (jwtUtil.validateToken(jwt!!, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        chain.doFilter(request, response)
    }
}
