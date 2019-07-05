package com.madadipouya.redis.springdata.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisSpringdataApplication

fun main(args: Array<String>) {
	runApplication<RedisSpringdataApplication>(*args)
}