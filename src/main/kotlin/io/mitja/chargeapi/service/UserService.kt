package io.mitja.chargeapi.service

import io.mitja.chargeapi.entity.CreateUser
import io.mitja.chargeapi.entity.UpdateUser
import io.mitja.chargeapi.entity.User
import io.mitja.chargeapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<User?> { return userRepository.findAll() }
    fun getUserByEmail(email: String): User? { return userRepository.findByEmail(email) }
    fun createNewUser(createUser: CreateUser): User {
        // Map CreateUser to User
        val user = User(
            id = 0, // ID will be auto-generated
            email = createUser.email,
            password = createUser.password,
            role = createUser.role
        )
        // Save and return the saved User
        return userRepository.save(user)
    }    fun deleteUser(id: Int) { userRepository.deleteById(id) }
    @Transactional
    fun updateUser(user: UpdateUser, id: Int): User? {
        val existingUser = userRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("User with ID $id not found")
        }

        user.email?.let { existingUser.email = it }
        user.password?.let { existingUser.password = it }
        user.role?.let { existingUser.role = it }

        return userRepository.save(existingUser)
    }

}