package com.example.composeproject.data.local.keyvalue

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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