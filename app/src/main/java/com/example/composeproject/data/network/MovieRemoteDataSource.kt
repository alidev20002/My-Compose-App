package com.example.composeproject.data.network

import android.util.Log
import com.example.composeproject.data.network.api.ApiMovie
import com.example.composeproject.data.network.model.FullMovie
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiMovie: ApiMovie
): MovieRemoteDataSourceInterface {

    override suspend fun getAllMovies(page: Int): List<FullMovie> {
        try {
            val response = apiMovie.getAllMovies(page, "")
            Log.i("alitest", "getAllMovies: $response")
            return response.body()?.data ?: emptyList()
        }catch (e: Exception) {
            Log.e("message", e.message.toString())
        }
        return emptyList()
    }
}