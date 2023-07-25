package com.example.composeproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val allMovies = movieRepository.getMovies()
    private var _movieDetail: FullMovie? = null
    val movieDetail: FullMovie?
        get() = _movieDetail

    fun updateMovieDetail(movie: FullMovie) {
        _movieDetail = movie
    }
}