package com.example.composeproject.data.local.db

import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.local.db.entities.Movie
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }
}