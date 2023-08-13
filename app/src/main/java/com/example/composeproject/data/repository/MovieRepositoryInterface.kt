package com.example.composeproject.data.repository

import androidx.paging.PagingData
import com.example.composeproject.data.network.model.FullMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
//    suspend fun syncMovies()
    fun getMovies(): Flow<PagingData<FullMovie>>
}