package com.example.composeproject.data.local.keyvalue

import kotlinx.coroutines.flow.Flow

interface PageDataStoreInterface {

    fun readPageNumber(): Flow<Int?>

    suspend fun writePageNumber(value: Int)
}