package com.example.notesapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.presentation.viewmodel.NoteViewModel
import java.text.DateFormat

@Composable
fun NoteListScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notesFlow.collectAsState(initial = emptyList())
    val username by viewModel.username.collectAsState("")
    val darkMode by viewModel.darkModePreference.collectAsState(initial = false)

    // Observe the dark mode preference to set the theme
    val colorScheme = if (darkMode) {
        darkColorScheme() // Dark theme colors
    } else {
        lightColorScheme() // Light theme colors
    }

    MaterialTheme(colorScheme = colorScheme) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Welcome, $username")
            LazyColumn {
                items(notes) { note ->
                    NoteItem(note = note, onDelete = { viewModel.deleteNote(note) })
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: NoteEntity, onDelete: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Created on: ${DateFormat.getDateInstance().format(note.timestamp)}")
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onDelete() }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete Note")
                }
            }
        }
    }
}