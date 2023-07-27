package com.example.clear.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.clear.room.dao.DictionaryDataBaseDao
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.model.Dictionary
import com.example.clear.room.model.Note
import com.example.clear.room.model.Todo
import com.example.clear.room.model.UriListConverter

@Database(entities = [Note::class , Todo::class , Dictionary::class] , version = 10 , exportSchema = false)
@TypeConverters(UriListConverter::class) // Add the UriListConverter here
abstract class ClearDataBase : RoomDatabase() {
    abstract fun noteDao() : NoteDataBaseDao
    abstract fun todoDao() : TodoDataBaseDao
    abstract fun dictionaryDao() :DictionaryDataBaseDao

}