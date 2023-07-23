package com.example.composeproject.data.local.db

import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.local.db.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val movieDao: MovieDao): MovieLocalDataSourceInterface {

    override fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>) {
        movieDao.insertOrIgnoreMovies(movies)
    }
}