package com.example.notesapp.data.repository

import com.example.notesapp.data.db.NoteDao
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.data.preferences.UserPreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val userPreferencesManager: UserPreferencesManager
) {

    // Flow to observe all notes
    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()

    // Function to add a new note
    suspend fun addNote(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    // Function to delete a note
    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    // User Preferences (DataStore) methods
    suspend fun saveUsername(username: String) {
        userPreferencesManager.saveUsername(username)
    }

    fun getUsername(): Flow<String> {
        return userPreferencesManager.getUsername()
    }

    suspend fun saveDarkModePreference(isDarkMode: Boolean) {
        userPreferencesManager.saveTheme(isDarkMode)
    }

    fun getDarkModePreference(): Flow<Boolean> {
        return userPreferencesManager.getTheme()
    }
}
