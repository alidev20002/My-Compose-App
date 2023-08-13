package com.example.composeproject.data.local.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composeproject.data.local.db.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :query || '%'")
    fun searchMovie(query: String): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>)

}