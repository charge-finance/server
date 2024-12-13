package io.mitja.chargeapi.config

import javax.crypto.spec.SecretKeySpec
import java.nio.charset.StandardCharsets

object SecurityConstants {
    const val SECRET = "Secret Key to generate JWT's (min 256 Bits)"
    const val ALGORITHM = "HmacSHA256"
    val SECRET_KEY_SPEC = SecretKeySpec(SECRET.toByteArray(StandardCharsets.UTF_8), ALGORITHM)
    const val EXPIRATION_TIME = 864_000_000L // 10 days
    const val AUTH_URLS = "/auth/*"
    val API_DOCUMENTATION_URLS = arrayOf(
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**"
    )

    const val AUTHORIZATION_HEADER_NAME = "Authorization"
}
