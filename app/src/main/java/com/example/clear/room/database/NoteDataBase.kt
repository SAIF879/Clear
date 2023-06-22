package com.example.clear.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.model.Note

@Database(entities = [Note::class] , version = 1 , exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao() : NoteDataBaseDao
}