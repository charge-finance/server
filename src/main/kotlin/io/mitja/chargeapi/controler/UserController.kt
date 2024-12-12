package io.mitja.chargeapi.controler

import io.mitja.chargeapi.entity.CreateUser
import io.mitja.chargeapi.entity.UpdateUser
import io.mitja.chargeapi.entity.User
import io.mitja.chargeapi.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<User?> {
        return userService.getAllUsers()
    }

    @GetMapping("/{email}")
    fun getUserByEmail(@PathVariable email: String): User? {
        return userService.getUserByEmail(email)
    }

    @PostMapping
    fun createNewUser(@RequestBody newUser: CreateUser): User? {
        return userService.createNewUser(newUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int) {
        userService.deleteUser(id)
    }

    @PutMapping("/{id}")
    fun updateUser(@RequestBody updateUser: UpdateUser, @PathVariable id: Int): User? {
        return userService.updateUser(updateUser, id)
    }
}