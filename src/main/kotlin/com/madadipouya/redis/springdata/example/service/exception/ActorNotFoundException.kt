package com.madadipouya.redis.springdata.example.service.exception

import java.lang.Exception

class ActorNotFoundException(override val message: String): Exception(message)