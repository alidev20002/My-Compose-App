package com.example.composeproject.data.local.db

import androidx.paging.PagingSource
import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.local.db.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalDataSourceInterface {

    override fun getAllMovies(): PagingSource<Int, MovieEntity> {
        return movieDao.getAllMovies()
    }

    override fun searchMovie(query: String): PagingSource<Int, MovieEntity> {
        return movieDao.searchMovie(query)
    }

    override suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>) {
        movieDao.insertOrIgnoreMovies(movies)
    }
}