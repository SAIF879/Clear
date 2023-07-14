package com.example.clear.di

import android.content.Context
import androidx.room.Room
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.database.ClearDataBase
import com.example.clear.room.model.TodoData
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
    fun provideNotesDao(noteDataBase: ClearDataBase): NoteDataBaseDao = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun providesTodoDao(todoDataBase : ClearDataBase) : TodoDataBaseDao = todoDataBase.todoDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): ClearDataBase =
        Room.databaseBuilder(context, ClearDataBase::class.java, "notes_db")
            .fallbackToDestructiveMigration().build()
}