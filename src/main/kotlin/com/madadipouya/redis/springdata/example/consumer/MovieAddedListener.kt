package com.madadipouya.redis.springdata.example.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.subscription.service.SubscriptionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class MovieAddedListener(val objectMapper: ObjectMapper, val subscriptionService: SubscriptionService) : MessageListener {

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MovieAddedListener::class.java)
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val movie = manualMap(message)
        subscriptionService.notifySubscribers(movie)
        logger.info("Notified on a new Movie creation {}, {}", movie.id, movie.name)
    }

    // Jackson is unable to deserialize empty list  of Actor. That's why need to manually map
    // Or not mixing model/entity with DTO
    fun manualMap(message: Message) : Movie {
        val movieMap = objectMapper.readValue(message.toString(), Map::class.java)
        val movie = Movie(movieMap["name"] as String, movieMap["genre"] as String, movieMap["year"] as Int)
        movie.id = movieMap["id"] as String?
        return movie
    }
}