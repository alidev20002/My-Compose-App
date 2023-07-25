package com.example.composeproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.local.db.entities.toFullMovieModel
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.network.model.toEntityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepositoryInterface {

    override suspend fun syncMovies() {
        val movies = movieRemoteDataSource.getAllMovies()
        movieLocalDataSource.insertOrIgnoreMovies(
            movies.map {
                it.toEntityModel()
            }
        )
    }

    override fun getMovies(): Flow<PagingData<FullMovie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { movieLocalDataSource.getAllMovies() }
        ).flow
            .mapPagingData { it.toFullMovieModel() }
    }
}

inline fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingData(
    crossinline transform: suspend (T) -> R
): Flow<PagingData<R>> {
    return map { pagingData ->
        pagingData.map { item ->
            transform(item)
        }
    }
}