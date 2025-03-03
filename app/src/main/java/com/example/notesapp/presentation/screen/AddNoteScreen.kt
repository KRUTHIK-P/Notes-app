package com.example.notesapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.presentation.viewmodel.NoteViewModel

@Composable
fun AddNoteScreen(viewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var isDarkMode by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input fields for title and content
        TextField(value = title, onValueChange = { title = it }, label = { Text("Note Title") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Note Content") })
        Spacer(modifier = Modifier.height(16.dp))

        // Username field
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        Button(onClick = { viewModel.saveUsername(username) }) {
            Text("Save Username")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dark mode toggle
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode")
            Spacer(modifier = Modifier.width(5.dp))
            Switch(checked = isDarkMode, onCheckedChange = { newValue ->
                isDarkMode = newValue
                viewModel.saveDarkModePreference(isDarkMode)
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save Note button
        Button(onClick = {
            if (title.isNotEmpty() && content.isNotEmpty()) {
                viewModel.addNote(title, content)
            }
        }) {
            Text("Save Note")
        }
    }
}

