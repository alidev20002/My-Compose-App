package com.example.composeproject.data.local.keyvalue

import android.content.Context
import kotlinx.coroutines.flow.Flow

class PageDataStore(
    private val context: Context
) {

    private val baseKeyValue = KeyValueBase(context, FILE_NAME)

    fun readPageNumber(key: String): Flow<Int?> {
        return baseKeyValue.readInt(key)
    }

    suspend fun writePageNumber(key: String, value: Int) {
        baseKeyValue.writeInt(key, value)
    }

    companion object {
        private const val FILE_NAME = "movie"
    }
}