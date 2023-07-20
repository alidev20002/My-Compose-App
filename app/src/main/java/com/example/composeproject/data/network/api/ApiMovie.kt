package com.example.composeproject.data.network.api

import com.example.composeproject.model.FullMovieData
import com.example.composeproject.model.MovieData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovie {

    @GET("movies")
    suspend fun getData(@Query("page") page: Int, @Query("q") query: String) : MovieData

    @GET("movies")
    suspend fun getData(@Query("q") query: String) : FullMovieData

    @GET("movies")
    suspend fun getAllMovies(@Query("page") page: Int, @Query("q") query: String) : FullMovieData


    companion object {
        var apiService: ApiMovie? = null
        fun getInstance() : ApiMovie {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiMovie::class.java)
            }
            return apiService!!
        }
    }

}