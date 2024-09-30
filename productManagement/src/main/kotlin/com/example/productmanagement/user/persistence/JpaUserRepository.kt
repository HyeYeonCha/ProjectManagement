package com.example.productmanagement.user.persistence

import com.example.productmanagement.user.domain.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JpaUserRepository(): UserRepository {

    fun findById(id: Long): User {

        // TODO search db and add exception
        return User(
            id = id,
            name = "name",
            mobile = "mobile",
        )
    }

    data class User(
        val id: Long,
        val name: String,
        val mobile: String,
    )
}