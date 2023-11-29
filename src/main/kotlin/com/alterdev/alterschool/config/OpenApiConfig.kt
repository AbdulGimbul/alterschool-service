package com.alterdev.alterschool.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Specification - Alterschool API",
        version = "v0.1",
        description = "API documentation for Alterschool Service",
        license = License(
            name = "Alterdev@2023",
            url = "https://alterdev.id"
        ),
        contact = Contact(
            name = "Alterdev",
            email = "alterdeveloper@gmail.com",
            url = "https://alterdev.id"
        ),
    ),
    servers = [
        Server(
            url = "https://alterschool-service.fly.dev/alterschool-service",
            description = "Production server"
        ),
        Server(
            url = "http://localhost:8000/alterschool-service",
            description = "Local server"
        )
    ],
    security = [
        SecurityRequirement(
            name = "bearerAuth"
        )
    ]
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth token",
    scheme = "Bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER
)
class OpenApiConfig