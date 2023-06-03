package com.madadipouya.redis.springdata.example.service.impl

import com.madadipouya.redis.springdata.example.controller.ActorController
import com.madadipouya.redis.springdata.example.model.Actor
import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.repository.ActorRepository
import com.madadipouya.redis.springdata.example.service.ActorService
import com.madadipouya.redis.springdata.example.service.MovieService
import com.madadipouya.redis.springdata.example.service.exception.MovieNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultActorService(val actorRepository: ActorRepository, val movieService: MovieService) : ActorService {

    override fun getActor(id: String) = actorRepository.findById(id).orElseThrow {
        MovieNotFoundException("Unable to find actor for $id id")
    }

    override fun getAllActors(): List<Actor> = actorRepository.findAll().toList()

    override fun updateActor(id: String, actorDto: ActorController.ActorDto): Actor {
        val actor = getActor(id).copy(actorDto.firstName, actorDto.lastName, actorDto.birthDate)
        actor.id = id
        return actorRepository.save(actor)
    }

    override fun createActor(actorDto: ActorController.ActorDto): Actor {
        return actorRepository.save(Actor(actorDto.firstName, actorDto.lastName, actorDto.birthDate))
    }

    override fun deleteActor(id: String) = actorRepository.deleteById(id)

    override fun addActorToMovie(actorId: String, movieId: String): Movie {
        val movie: Movie = movieService.getMovie(movieId)
        val actor: Actor = getActor(actorId)
        (movie.actors as ArrayList).add(actor)
        return movieService.updateMovie(movie)
    }
}