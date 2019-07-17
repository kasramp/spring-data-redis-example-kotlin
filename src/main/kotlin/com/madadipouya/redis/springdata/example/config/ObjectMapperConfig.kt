package com.madadipouya.redis.springdata.example.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {

    fun getObjectMapper() = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}