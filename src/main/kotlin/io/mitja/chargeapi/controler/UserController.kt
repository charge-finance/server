package io.mitja.chargeapi.controler

import io.mitja.chargeapi.entity.UpdateUser
import io.mitja.chargeapi.entity.User
import io.mitja.chargeapi.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {


    @Operation(summary = "Retrieve all users", description = "Fetch a list of all users in the database.")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully.")
    @GetMapping
    fun getAllUsers(): List<User?> {
        return userService.getAllUsers()
    }

    @Operation(summary = "Retrieve a user by email", description = "Fetch a user's details using their email address.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "User found successfully.",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = User::class))]
            ),
            ApiResponse(responseCode = "404", description = "User not found.")
        ]
    )
    @GetMapping("/{email}")
    fun getUserByEmail(@PathVariable email: String): User? {
        return userService.getUserByEmail(email)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int) {
        userService.deleteUser(id)
    }

    @Operation(summary = "Update a user", description = "Update an existing user's details using their ID.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "User updated successfully.",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = User::class))]
            ),
            ApiResponse(responseCode = "404", description = "User not found."),
            ApiResponse(responseCode = "400", description = "Invalid input.")
        ]
    )
    @PutMapping("/{id}")
    fun updateUser(
        @RequestBody(
            description = "Updated details of the user", required = true, content = [
                Content(mediaType = "application/json", schema = Schema(implementation = UpdateUser::class))
            ]
        ) updateUser: UpdateUser,
        @PathVariable id: Int
    ): User? {
        return userService.updateUser(updateUser, id)
    }
}
