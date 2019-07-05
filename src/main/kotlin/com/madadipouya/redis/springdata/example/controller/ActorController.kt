package com.madadipouya.redis.springdata.example.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.madadipouya.redis.springdata.example.model.Actor
import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.service.ActorService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

@RestController
@RequestMapping("/v1/actors")
class ActorController(val actorService: ActorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private fun createActor(@Validated actor: ActorDto): Actor = actorService.createActor(actor)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getActorById(@PathVariable id: String): Actor = actorService.getActor(id)

    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateActor(@PathVariable id: String, @Validated actor: ActorDto): Actor = actorService.updateActor(id, actor)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private fun getActors(): List<Actor> = actorService.getAllActors()

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteActor(id: String) = actorService.deleteActor(id)

    @PatchMapping(value = ["/{actorId}/link/{movieId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun addActorToMovie(@PathVariable actorId: String, @PathVariable movieId: String): Movie {
        return actorService.addActorToMovie(actorId, movieId)
    }

    data class ActorDto(
            @get:NotBlank val firstName: String,
            @get:NotBlank val lastName: String,
            @field:DateTimeFormat(pattern = "yyyy-MM-dd")
            @field:JsonDeserialize(using = LocalDateDeserializer::class)
            @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @get:Past val birthDate: LocalDate
    )
}