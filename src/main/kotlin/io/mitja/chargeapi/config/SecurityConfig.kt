package io.mitja.chargeapi.config

import io.mitja.chargeapi.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class SecurityConfig(private val tokenService: TokenService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(SecurityConstants.AUTH_URLS).permitAll() // Allow access without authentication
                    .requestMatchers(*SecurityConstants.API_DOCUMENTATION_URLS).permitAll() // Allow access to Swagger
                    .anyRequest().authenticated() // Protect all other endpoints
            }
            .addFilterBefore(TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    class TokenAuthenticationFilter(private val tokenService: TokenService) : OncePerRequestFilter() {
        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
        ) {
            val token = resolveToken(request)
            val username = token?.let { tokenService.validateToken(it) }

            if (username != null) {
                SecurityContextHolder.getContext().authentication =
                    UsernamePasswordAuthenticationToken(username, null, emptyList())
            }

            filterChain.doFilter(request, response)
        }

        private fun resolveToken(request: HttpServletRequest): String? {
            val bearerToken = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER_NAME)
            return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                bearerToken.substring(7)
            } else null
        }
    }
}
