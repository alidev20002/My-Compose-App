package com.example.composeproject.data.network

import com.example.composeproject.data.network.model.FullMovie

interface MovieRemoteDataSourceInterface {
    suspend fun getAllMovies(page: Int): List<FullMovie>
}