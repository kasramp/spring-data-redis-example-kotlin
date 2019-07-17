package com.madadipouya.redis.springdata.example.producer

import com.madadipouya.redis.springdata.example.model.Movie
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class MovieAddedProducer(val template: RedisTemplate<String, Any>, val channelTopic: ChannelTopic) {

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MovieAddedProducer::class.java)
    }

    fun publish(movie: Movie) {
        logger.info("Notifying subscribers on adding a new Movie {} {}", movie.id, movie.name)
        template.convertAndSend(channelTopic.topic, movie)
    }
}