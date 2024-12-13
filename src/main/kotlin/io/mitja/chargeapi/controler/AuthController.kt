package io.mitja.chargeapi.controler

import io.mitja.chargeapi.entity.CreateUser
import io.mitja.chargeapi.entity.User
import io.mitja.chargeapi.service.TokenService
import io.mitja.chargeapi.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
class AuthController(private val userService: UserService, private val tokenService: TokenService) {

    private val passwordEncoder = BCryptPasswordEncoder()

    @Operation(summary = "Log in to the application", description = "Log in using email and password to receive an authentication token.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Login successful.",
                content = [Content(mediaType = "application/json")]),
            ApiResponse(responseCode = "400", description = "Invalid email or password.",
                content = [Content(mediaType = "text/plain")])
        ]
    )
    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam password: String): ResponseEntity<Any> {
        val user = userService.getUserByEmail(email)
            ?: return ResponseEntity.badRequest().body("Invalid email or password")

        return if (passwordEncoder.matches(password, user.password)) {
            val claims = mapOf("email" to user.email) // Create a map for the claims
            val token = tokenService.generateToken(claims)
            ResponseEntity.ok(mapOf("token" to token))
        } else {
            ResponseEntity.badRequest().body("Invalid email or password")
        }
    }

    @Operation(summary = "Register a new user", description = "Register a new user by providing user details.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "User created successfully.",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = User::class))]
            ),
            ApiResponse(responseCode = "400", description = "Invalid input.")
        ]
    )
    @PostMapping("/register")
    fun registerNewUser(@RequestBody newUser: CreateUser): User? {
        return userService.createNewUser(newUser)
    }

}