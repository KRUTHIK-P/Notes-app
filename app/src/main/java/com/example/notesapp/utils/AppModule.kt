package com.example.notesapp.utils

import android.content.Context
import com.example.notesapp.data.db.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context) = NoteDB.getDatabase(context).noteDao()
}