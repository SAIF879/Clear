package com.example.clear.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clear.room.dao.DictionaryDataBaseDao
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.model.Dictionary
import com.example.clear.room.model.Note
import com.example.clear.room.model.Todo

@Database(entities = [Note::class , Todo::class , Dictionary::class] , version = 6 , exportSchema = false)

abstract class ClearDataBase : RoomDatabase() {
    abstract fun noteDao() : NoteDataBaseDao
    abstract fun todoDao() : TodoDataBaseDao
    abstract fun dictionaryDao() :DictionaryDataBaseDao

}