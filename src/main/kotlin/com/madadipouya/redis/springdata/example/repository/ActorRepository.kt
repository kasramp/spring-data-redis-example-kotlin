package com.madadipouya.redis.springdata.example.repository

import com.madadipouya.redis.springdata.example.model.Actor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ActorRepository : CrudRepository<Actor, String>