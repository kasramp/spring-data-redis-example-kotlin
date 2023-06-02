package com.madadipouya.redis.springdata.example.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    fun apiInfo() = OpenAPI().info(
        Info().title("Spring Data Redis Example")
            .description("Spring Data Redis Example").version("v0.0.2")
            .contact(contactDetails()).license(licenseDetails())
    )

    fun contactDetails() = Contact().name("Kasra Madadipouya")
        .email("kasra@madadipouya.com")
        .url("https://geekyhacker.com")

    fun licenseDetails() = License().name("GNU General Public License v3.0")
        .url("https://github.com/kasramp/spring-data-redis-example-kotlin/blob/master/LICENSE")
}