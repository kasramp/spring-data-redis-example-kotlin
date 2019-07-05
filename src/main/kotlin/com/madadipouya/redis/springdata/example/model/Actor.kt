package com.madadipouya.redis.springdata.example.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDate

@RedisHash("Actors")
data class Actor(
        @Indexed val firstName: String,
        val lastName: String,
        val birthDate: LocalDate
) {
    @get:Id
    var id: String? = null
}