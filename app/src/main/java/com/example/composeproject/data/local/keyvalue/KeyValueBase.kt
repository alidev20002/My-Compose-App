package com.example.composeproject.data.local.keyvalue

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "movies")

class KeyValueBase(
    private val context: Context,
    private val fileName: String
): KeyValueInterface {

    override fun readInt(key: String): Flow<Int?> {
        val value = context.dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }
        return value
    }

    override suspend fun writeInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }
}