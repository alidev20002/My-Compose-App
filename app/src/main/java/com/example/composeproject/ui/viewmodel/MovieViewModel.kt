package com.example.composeproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.composeproject.data.network.model.FullMovie
import com.example.composeproject.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _allMovies = MutableStateFlow<PagingData<FullMovie>>(PagingData.empty())
    val allMovies = _allMovies.asStateFlow()

    private var _movieDetail: FullMovie? = null
    val movieDetail: FullMovie?
        get() = _movieDetail

    init {
        viewModelScope.launch {
            movieRepository.getMovies("").collect {
                _allMovies.emit(it)
            }
        }
    }

    fun updateMovieDetail(movie: FullMovie) {
        _movieDetail = movie
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            movieRepository.getMovies(query).collect {
                _allMovies.emit(it)
            }
        }
    }

}