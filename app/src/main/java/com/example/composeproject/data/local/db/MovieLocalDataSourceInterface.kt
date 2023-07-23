package com.example.composeproject.data.local.db

import com.example.composeproject.data.local.db.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSourceInterface {
    fun getAllMovies(): Flow<List<MovieEntity>>
    suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>)
}