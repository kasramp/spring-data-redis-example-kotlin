package com.madadipouya.redis.springdata.example.config

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

class RedisConfig {

    @Bean
    fun jedisConnectionFactory() = JedisConnectionFactory()

    @Bean
    fun redisTemplate() : RedisTemplate<String, Any> {
        val template : RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }
}