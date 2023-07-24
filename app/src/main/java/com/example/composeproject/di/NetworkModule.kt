package com.example.composeproject.di

import com.example.composeproject.data.network.api.ApiMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiMovie(): ApiMovie {
        return Retrofit.Builder()
                .baseUrl("https://moviesapi.ir/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiMovie::class.java)
    }
}