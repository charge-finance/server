package io.mitja.chargeapi.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.mitja.chargeapi.config.SecurityConstants
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService {

    // Secret key for signing tokens
    private val secretKey = SecurityConstants.SECRET_KEY_SPEC

    // Token expiration time
    private val expirationTimeMillis: Int = SecurityConstants.EXPIRATION_TIME

    /**
     * Generate a JWT with the given claims and expiration time.
     *
     * @param claims A map of claims to be included in the token.
     * @return A signed JWT as a string.
     */
    fun generateToken(claims: Map<String, Any>): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationTimeMillis)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    /**
     * Validate the token and return its claims if valid.
     *
     * @param token The JWT to validate.
     * @return Claims from the token if it is valid.
     * @throws io.jsonwebtoken.JwtException if the token is invalid or expired.
     */
    fun validateToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
