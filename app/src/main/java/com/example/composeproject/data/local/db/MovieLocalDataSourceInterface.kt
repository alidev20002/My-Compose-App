package com.example.composeproject.data.local.db

import androidx.paging.PagingSource
import com.example.composeproject.data.local.db.entities.MovieEntity

interface MovieLocalDataSourceInterface {
    fun getAllMovies(): PagingSource<Int, MovieEntity>
    fun searchMovie(query: String): PagingSource<Int, MovieEntity>
    suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>)
}