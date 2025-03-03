package com.example.notesapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // Extension property to get a DataStore instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    suspend fun saveUsername(username: String) {
        this.context.dataStore.edit {
            it[USERNAME_KEY] = username
        }
    }

    fun getUsername() = context.dataStore.data.map {
        it[USERNAME_KEY] ?: ""
    }

    suspend fun saveTheme(isDarkMode: Boolean) {
        context.dataStore.edit {
            it[DARK_MODE_KEY] = isDarkMode
        }
    }

    fun getTheme() = context.dataStore.data.map {
        it[DARK_MODE_KEY] ?: false
    }

}
