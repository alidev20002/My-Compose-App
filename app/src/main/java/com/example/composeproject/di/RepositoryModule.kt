package com.example.composeproject.di

import com.example.composeproject.data.repository.MovieRepository
import com.example.composeproject.data.repository.MovieRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(
        repository: MovieRepository
    ): MovieRepositoryInterface
}