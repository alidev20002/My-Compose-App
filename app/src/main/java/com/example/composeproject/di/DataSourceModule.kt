package com.example.composeproject.di

import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.local.db.MovieLocalDataSourceInterface
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindMovieLocalDataSource(
        dataSource: MovieLocalDataSource
    ) : MovieLocalDataSourceInterface

}