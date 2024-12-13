package io.mitja.chargeapi.config

import io.jsonwebtoken.security.Keys
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey

object SecurityConstants {
    const val EXPIRATION_TIME = 3600000 // 1 hour in milliseconds
    private const val SECRET = "your-256-bit-secret-your-256-bit-secret" // Replace with a secure random string
    val SECRET_KEY_SPEC: SecretKey = Keys.hmacShaKeyFor(SECRET.toByteArray())
    const val AUTH_URLS = "/auth/*"
    val API_DOCUMENTATION_URLS = arrayOf(
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**"
    )

    const val AUTHORIZATION_HEADER_NAME = "Authorization"
}
