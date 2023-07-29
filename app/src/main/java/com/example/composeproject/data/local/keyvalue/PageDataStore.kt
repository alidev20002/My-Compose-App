package com.example.composeproject.data.local.keyvalue

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PageDataStore(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val PAGE_NUMBER_KEY = intPreferencesKey("page_number")
    }

    val pageNumber: Flow<Int?>
        get() = dataStore.data.map { preferences ->
            preferences[PAGE_NUMBER_KEY] ?: 1
        }

    suspend fun savePage(page: Int) {
        dataStore.edit { preferences ->
            preferences[PAGE_NUMBER_KEY] = page
        }
    }
}