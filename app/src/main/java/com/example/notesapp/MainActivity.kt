package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notesapp.presentation.screen.AddNoteScreen
import com.example.notesapp.presentation.screen.NoteListScreen
import com.example.notesapp.presentation.viewmodel.NoteViewModel
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Notes") })
                    },
                    content = { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            val viewModel: NoteViewModel = hiltViewModel()
                            AddNoteScreen(viewModel = viewModel)
                            NoteListScreen(viewModel = viewModel)
                        }
                    }
                )
            }
        }
    }
}