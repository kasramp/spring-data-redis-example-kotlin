package com.madadipouya.redis.springdata.example.repository

import com.madadipouya.redis.springdata.example.model.Actor
import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.time.LocalDate

@Testcontainers
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class ActorRepositoryTest {

    @Autowired
    private lateinit var actorRepository: ActorRepository

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

    @BeforeEach
    fun setUp() {
        actorRepository.deleteAll()
    }

    @Test
    fun `should find an Actor by id`() {
        val actorId = createActor("Keanu", "Reeves", LocalDate.of(1964, 9, 2))

        val result = actorRepository.findById(actorId)

        assertTrue(result.isPresent)
        val actor = result.get()
        assertEquals("Keanu", actor.firstName)
        assertEquals("Reeves", actor.lastName)
        assertEquals(LocalDate.of(1964, 9, 2), actor.birthDate)
    }

    @Test
    fun `should update an Actor`() {
        val actorId = createActor("Keanu", "Reeves", LocalDate.of(1964, 9, 2))

        val actor = actorRepository.findById(actorId).map {
            val updatedActor = Actor(it.firstName, it.lastName, LocalDate.of(1969, 9, 9))
            updatedActor.id = it.id
            updatedActor
        }.get()

        actorRepository.save(actor)

        val result = actorRepository.findById(actorId)
        assertTrue(result.isPresent)
        val updatedActor = result.get()
        assertEquals("Keanu", updatedActor.firstName)
        assertEquals("Reeves", updatedActor.lastName)
        assertEquals(LocalDate.of(1969, 9, 9), updatedActor.birthDate)
    }

    fun createActor(firstName: String, lastName: String, birthDay: LocalDate): String {
        val result = actorRepository.save(Actor(firstName, lastName, birthDay))
        return result.id!!
    }
}