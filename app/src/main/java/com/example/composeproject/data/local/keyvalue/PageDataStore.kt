package com.example.composeproject.data.local.keyvalue

import android.content.Context
import kotlinx.coroutines.flow.Flow

class PageDataStore(
    private val context: Context
): PageDataStoreInterface {

    private val baseKeyValue = KeyValueBase(context, FILE_NAME)

    override fun readPageNumber(): Flow<Int?> {
        return baseKeyValue.readInt(PAGE_NUMBER_KEY)
    }

    override suspend fun writePageNumber(value: Int) {
        baseKeyValue.writeInt(PAGE_NUMBER_KEY, value)
    }

    companion object {
        private const val FILE_NAME = "movie"
        private const val PAGE_NUMBER_KEY = "page"
    }
}