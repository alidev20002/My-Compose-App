package com.example.composeproject.data.repositories

import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.model.FullMovie

class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun fetchMovies(): List<FullMovie> {
        val movies = movieRemoteDataSource.getAllMovies()
        movies.forEach {
            movieLocalDataSource.insertMovie(it)
        }
    }
}