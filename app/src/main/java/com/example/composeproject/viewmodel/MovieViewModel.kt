package com.example.composeproject.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproject.api.ApiMovie
import com.example.composeproject.model.FullMovieData
import com.example.composeproject.model.MovieData
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    var movieListResponse: MovieData by mutableStateOf(MovieData(listOf()))
    private var page: Int by mutableStateOf(1)
    private var errorMessage: String by mutableStateOf("")
    private var query: String by mutableStateOf("")
    private var fullMovieQuery: String by mutableStateOf("")

    var fullMovieResponse: FullMovieData by mutableStateOf(FullMovieData(listOf()))

    fun getMovieList() {
        viewModelScope.launch {
            val apiService = ApiMovie.getInstance()
            try {
                val movieList = apiService.getData(page, query)
                movieListResponse = movieList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("message", errorMessage)
            }
        }
    }

    fun getFullMovie() {
        viewModelScope.launch {
            val apiService = ApiMovie.getInstance()
            try {
                val movieList = apiService.getData(fullMovieQuery)
                fullMovieResponse = movieList
                Log.e("message", fullMovieResponse.data[0].title)
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("message", errorMessage)
            }
        }
    }

    fun nextPage() {
        if (page < 25)
            page = page.inc()
    }

    fun previousPage() {
        if (page > 1)
            page = page.dec()
    }

    fun updateQuery(input: String) {
        query = input
        page = 1
    }

    fun updateFullMovieQuery(input: String) {
        fullMovieQuery = input
    }

}