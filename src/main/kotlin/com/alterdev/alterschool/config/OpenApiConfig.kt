package com.alterdev.alterschool.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
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
    )
)
class OpenApiConfig