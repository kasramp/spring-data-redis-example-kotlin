package com.madadipouya.redis.springdata.example.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("Movies")
data class Movie(
        @Indexed val name: String,
        val genre: String,
        val year: Int
) {
    @get:Id
    var id: String? = null
    @Indexed @get:Reference var actors: List<Actor>? = listOf()
}