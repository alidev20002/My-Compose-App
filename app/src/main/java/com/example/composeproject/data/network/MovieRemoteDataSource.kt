package com.example.composeproject.data.network

import android.util.Log
import com.example.composeproject.data.network.api.ApiMovie
import com.example.composeproject.data.network.model.FullMovie

class MovieRemoteDataSource(private val apiMovie: ApiMovie) {

    suspend fun getAllMovies(): List<FullMovie> {
        try {
            val response = apiMovie.getAllMovies(0, "")
            Log.i("alitest", "getAllMovies: $response")
            return response.body()?.data ?: emptyList()
        }catch (e: Exception) {
            Log.e("message", e.message.toString())
        }
        return emptyList()
    }
}