package com.madadipouya.redis.springdata.example.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class RedisConfig(val messageListener: MessageListener) {

    @Value("\${spring.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    lateinit var redisPort: String

    @Value("\${spring.redis.topic}")
    lateinit var redisTopic: String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        val factory = JedisConnectionFactory(config, jedisClientConfiguration)
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory())
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }

    @Bean
    fun topic(): ChannelTopic = ChannelTopic(redisTopic)

    @Bean
    fun newMessageListener(): MessageListenerAdapter = MessageListenerAdapter(messageListener)

    @Bean
    fun redisContainer(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(jedisConnectionFactory())
        container.addMessageListener(newMessageListener(), topic())
        return container
    }
}