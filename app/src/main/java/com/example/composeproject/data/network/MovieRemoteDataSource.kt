package com.example.composeproject.data.network

import com.example.composeproject.data.network.api.ApiMovie
import com.example.composeproject.model.FullMovie

class MovieRemoteDataSource(private val apiMovie: ApiMovie) {

    suspend fun getAllMovies(): List<FullMovie> {
        val movieData = apiMovie.getAllMovies(0, "")
        return movieData.data
    }
}