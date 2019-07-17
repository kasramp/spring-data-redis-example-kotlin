package com.madadipouya.redis.springdata.example.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import com.madadipouya.redis.springdata.example.model.Movie
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class MovieAddedListener(val objectMapper: ObjectMapper) : MessageListener {

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MovieAddedListener::class.java)
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val movieMap = objectMapper.readValue(message.toString(), Map::class.java)
        logger.info("Notified on a new Movie creation {}, {}", movieMap["id"], movieMap["name"])
    }

}