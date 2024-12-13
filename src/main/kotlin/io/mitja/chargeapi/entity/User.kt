package io.mitja.chargeapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var email: String,
    var password: String,
    var role: Role
)

data class CreateUser(
    var email: String,
    var password: String,
    var role: Role
)

data class UpdateUser(
    val email: String?,
    val password: String?,
    val role: Role?
)

enum class Role {
    USER, ADMIN
}