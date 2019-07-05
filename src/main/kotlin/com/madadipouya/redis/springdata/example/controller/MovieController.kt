package com.madadipouya.redis.springdata.example.controller

import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.repository.MovieRepository
import com.madadipouya.redis.springdata.example.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

@RestController
@RequestMapping("/v1/movies")
class MovieController(val movieService: MovieService, val movieRepository: MovieRepository) {

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

    data class MovieDto(
            @get:NotBlank val name: String?,
            @get:NotBlank val genre: String?,
            @get:Min(value = 1900) @PastOrPresent val year: Int
    )
}