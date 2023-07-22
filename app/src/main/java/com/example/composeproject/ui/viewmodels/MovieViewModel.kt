package com.example.composeproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _allMovies: MutableStateFlow<List<FullMovie>> = MutableStateFlow(emptyList())
    val allMovies: StateFlow<List<FullMovie>> = _allMovies.asStateFlow()
}