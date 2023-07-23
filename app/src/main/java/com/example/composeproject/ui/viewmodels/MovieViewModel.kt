package com.example.composeproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composeproject.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    val allMovies = movieRepository.getMovies()

    fun syncMovies() {
        viewModelScope.launch {
            movieRepository.syncMovies()
        }
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}