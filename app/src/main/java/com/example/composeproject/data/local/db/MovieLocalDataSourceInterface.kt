package com.example.composeproject.data.local.db

import androidx.paging.PagingSource
import com.example.composeproject.data.local.db.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSourceInterface {
    fun getAllMovies(): PagingSource<Int, MovieEntity>
    suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>)
}