package com.madadipouya.redis.springdata.example.repository

import com.madadipouya.redis.springdata.example.model.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : CrudRepository<Movie, String>