package com.madadipouya.redis.springdata.example.service

import com.madadipouya.redis.springdata.example.controller.MovieController
import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.service.exception.MovieNotFoundException

interface MovieService {

    @Throws(MovieNotFoundException::class)
    fun getMovie(id: String): Movie

    fun getAllMovies(): List<Movie>

    @Throws(MovieNotFoundException::class)
    fun updateMovie(id: String, movieDto: MovieController.MovieDto): Movie

    fun updateMovie(movie: Movie): Movie

    fun createMovie(movieDto: MovieController.MovieDto) : Movie

    @Throws(MovieNotFoundException::class)
    fun deleteMovie(id: String)
}