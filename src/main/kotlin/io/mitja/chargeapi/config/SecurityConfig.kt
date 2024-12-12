package io.mitja.chargeapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Disable CSRF (only if you fully understand the implications)
            .authorizeHttpRequests { auth ->
                auth
                    .anyRequest().permitAll() // Permit all requests without authorization
            }

        return http.build()
    }
}
