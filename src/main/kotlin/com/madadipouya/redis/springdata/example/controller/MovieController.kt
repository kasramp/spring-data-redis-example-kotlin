package com.madadipouya.redis.springdata.example.controller

import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.service.MovieService
import com.madadipouya.redis.springdata.example.subscription.model.Subscriber
import com.madadipouya.redis.springdata.example.subscription.service.SubscriptionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent

@RestController
@RequestMapping("/v1/movies")
class MovieController(val movieService: MovieService, val subscriptionService: SubscriptionService) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private fun createMovie(@Valid movie: MovieDto): Movie = movieService.createMovie(movie)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieById(@PathVariable id: String): Movie = movieService.getMovie(id)

    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateMovie(@PathVariable id: String, @Validated movie: MovieDto): Movie = movieService.updateMovie(id, movie)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private fun getMovies(): List<Movie> = movieService.getAllMovies()

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteMovie(id: String) = movieService.deleteMovie(id)


    @GetMapping(value = ["/subscribe"], produces = [APPLICATION_STREAM_JSON_VALUE])
    private fun subscribeToMovie(): Subscriber = subscriptionService.subscribe(Subscriber())

    data class MovieDto(
            @get:NotBlank val name: String?,
            @get:NotBlank val genre: String?,
            @get:Min(value = 1900) @PastOrPresent val year: Int
    )
}