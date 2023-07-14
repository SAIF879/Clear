package com.example.clear.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.model.Note
import com.example.clear.room.model.Todo

@Database(entities = [Note::class , Todo::class] , version = 2 , exportSchema = false)
abstract class ClearDataBase : RoomDatabase() {
    abstract fun noteDao() : NoteDataBaseDao
    abstract fun todoDao() : TodoDataBaseDao
}