package com.example.composeproject.di

import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.local.db.MovieLocalDataSourceInterface
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.MovieRemoteDataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindMovieLocalDataSource(
        dataSource: MovieLocalDataSource
    ): MovieLocalDataSourceInterface

    @Binds
    fun bindMovieRemoteDataSource(
        dataSource: MovieRemoteDataSource
    ): MovieRemoteDataSourceInterface

}