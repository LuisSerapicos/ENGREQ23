package com.example.happibee.Data.PreferencesDataStore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStoreManager private constructor(private val context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: DataStoreManager? = null

        fun getInstance(context: Context): DataStoreManager {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreManager(context)
                INSTANCE = instance
                instance
            }
        }
    }

    private val Context.dataStore by preferencesDataStore("settings")

    suspend fun saveName(name: String) {
        val dataStoreKey = stringPreferencesKey("name")
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = name
        }
    }

    suspend fun getName(): String {
        val dataStoreKey = stringPreferencesKey("name")
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey] ?: ""
    }
}