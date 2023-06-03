package com.madadipouya.redis.springdata.example

import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@ExtendWith(SpringExtension::class)
@SpringBootTest
class RedisSpringdataApplicationTests {

    companion object {
        @Container
        private val redisContainer = RedisContainer(DockerImageName.parse("redis:latest"))

        @JvmStatic
        @DynamicPropertySource
        fun redisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.repositories.enabled") { true }
            registry.add("spring.redis.host") { redisContainer.host }
            registry.add("spring.redis.port") { redisContainer.firstMappedPort }
            registry.add("spring.redis.topic") { "movie.update" }
        }
    }

    @Test
    fun contextLoads() {
    }
}
