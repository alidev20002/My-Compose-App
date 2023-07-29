package com.example.composeproject.data.local.keyvalue

import kotlinx.coroutines.flow.Flow

interface KeyValueInterface {

    fun readInt(key: String): Flow<Int>

    suspend fun writeInt(key: String, value: Int)
}