package com.example.composeproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _allMovies: MutableStateFlow<List<FullMovie>> = MutableStateFlow(emptyList())
    val allMovies: StateFlow<List<FullMovie>> = _allMovies.asStateFlow()

    fun getAllMovies() {
        viewModelScope.launch {
            movieRepository.updateMovies()
            val movies = movieRepository.fetchMovies()
            movies.collect {
                _allMovies.value = it
            }
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