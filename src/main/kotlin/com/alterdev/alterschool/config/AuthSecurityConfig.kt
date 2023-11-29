package com.alterdev.alterschool.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.HandlerExceptionResolver

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class AuthSecurityConfig(
    private val authProvider: AuthenticationProvider
) {

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private lateinit var exceptionResolver: HandlerExceptionResolver

    @Bean
    fun jwtAuthFilter(): JwtAuthFilter {
        return JwtAuthFilter(exceptionResolver)
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(*PATH_ARRAY)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    companion object {
        //    private final TokenWhitelist tokenWhitelist;
        val PATH_ARRAY = arrayOf(
            "/api/auth/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs"
        )

    }
}