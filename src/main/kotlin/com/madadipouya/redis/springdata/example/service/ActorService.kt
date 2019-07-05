package com.madadipouya.redis.springdata.example.service

import com.madadipouya.redis.springdata.example.controller.ActorController
import com.madadipouya.redis.springdata.example.model.Actor
import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.service.exception.ActorNotFoundException
import com.madadipouya.redis.springdata.example.service.exception.MovieNotFoundException

interface ActorService {

    @Throws(ActorNotFoundException::class)
    fun getActor(id: String): Actor

    fun getAllActors(): List<Actor>

    @Throws(ActorNotFoundException::class)
    fun updateActor(id: String, actorDto: ActorController.ActorDto): Actor

    fun createActor(actorDto: ActorController.ActorDto): Actor

    @Throws(ActorNotFoundException::class)
    fun deleteActor(id: String)

    @Throws(ActorNotFoundException::class, MovieNotFoundException::class)
    fun addActorToMovie(actorId: String, movieId: String): Movie
}