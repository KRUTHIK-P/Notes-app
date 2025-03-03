# NotesApp

NotesApp is a simple note-taking application built using Jetpack Compose and Hilt for dependency injection. The app allows users to add, view, update, and delete notes. It also supports saving user preferences such as username and dark mode preference.

## Features

- Add, view, update, and delete notes
- Save and display username
- Toggle dark mode preference
- Uses Jetpack Compose for UI
- Hilt for dependency injection
- Room for local database storage
- DataStore for user preferences

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.5+
- Gradle 7.0+

### Installation

1. Clone the repository:
   git clone https://github.com/yourusername/notesapp.git
2. Open the project in Android Studio.
3. Build the project to download dependencies.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Project Structure

- `app/src/main/java/com/example/notesapp/`: Contains the main application code.
- `presentation/screen/`: Contains the UI screens.
- `presentation/viewmodel/`: Contains the ViewModel classes.
- `data/db/`: Contains the Room database entities and DAO.
- `data/repository/`: Contains the repository classes.
- `data/preferences/`: Contains the DataStore preferences manager.

## Dependencies

- Jetpack Compose
- Hilt
- Room
- DataStore
- Kotlin Coroutines
