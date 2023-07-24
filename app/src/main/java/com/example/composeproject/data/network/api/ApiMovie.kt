package com.example.composeproject.data.network.api

import com.example.composeproject.data.network.model.FullMovieData
import com.example.composeproject.data.network.model.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovie {

    @GET("movies")
    suspend fun getData(@Query("page") page: Int, @Query("q") query: String) : MovieData

    @GET("movies")
    suspend fun getData(@Query("q") query: String) : FullMovieData

    @GET("movies")
    suspend fun getAllMovies(@Query("page") page: Int, @Query("q") query: String) : Response<FullMovieData>

}