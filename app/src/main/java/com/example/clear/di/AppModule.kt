package com.example.clear.di

import android.content.Context
import androidx.room.Room
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.database.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//modules are used to add bindings to hilt
@InstallIn(SingletonComponent::class) // this will not be recreated , just one instance
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDataBase: NoteDataBase): NoteDataBaseDao = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): NoteDataBase =
        Room.databaseBuilder(context, NoteDataBase::class.java, "notes_db")
            .fallbackToDestructiveMigration().build()
}