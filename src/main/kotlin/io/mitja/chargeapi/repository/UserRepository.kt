package io.mitja.chargeapi.repository

import io.mitja.chargeapi.entity.CreateUser
import io.mitja.chargeapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}