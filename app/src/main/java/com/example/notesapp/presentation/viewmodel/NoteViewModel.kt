package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    // Coroutine job to manage background tasks
    private val viewModelJob = SupervisorJob()
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Flow to collect notes from Room database
    val notesFlow: StateFlow<List<NoteEntity>> = noteRepository.allNotes
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Flow for username preference
    val username: Flow<String> = noteRepository.getUsername()

    // Flow for dark mode preference
    val darkModePreference: Flow<Boolean> = noteRepository.getDarkModePreference()

    // Function to add a note
    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            val note = NoteEntity(title = title, content = content)
            noteRepository.addNote(note)
        }
    }

    // Function to delete a note
    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    // Function to save username preference
    fun saveUsername(username: String) {
        viewModelScope.launch {
            noteRepository.saveUsername(username)
        }
    }

    // Function to save dark mode preference
    fun saveDarkModePreference(isDarkMode: Boolean) {
        viewModelScope.launch {
            noteRepository.saveDarkModePreference(isDarkMode)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel() // Clean up coroutines when ViewModel is destroyed
    }
}
