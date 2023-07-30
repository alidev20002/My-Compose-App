package com.example.composeproject.di

import com.example.composeproject.data.local.keyvalue.PageDataStore
import com.example.composeproject.data.local.keyvalue.PageDataStoreInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PageDataStoreModule {

    @Binds
    @Singleton
    fun bindPageDataStore(
        pageDataStore: PageDataStore
    ): PageDataStoreInterface
}