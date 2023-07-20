package com.example.composeproject.data.repositories

import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.local.db.entities.toFullMovieModel
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.network.model.toEntityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun updateMovies() {
        val movies = movieRemoteDataSource.getAllMovies()
        movieLocalDataSource.insertMovies(
            movies.map {
                it.toEntityModel()
            }
        )
    }

    fun fetchMovies(): Flow<List<FullMovie>> {
        return movieLocalDataSource.getAllMovies().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieEntity.toFullMovieModel()
            }
        }
    }
}