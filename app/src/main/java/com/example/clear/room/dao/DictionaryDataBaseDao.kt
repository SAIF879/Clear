package com.example.clear.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clear.room.model.Dictionary
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDataBaseDao {

    @Query("SELECT * from dictionary_table WHERE is_saved = 1 ")
    fun getSavedWords() : Flow<List<Dictionary>>

    @Query("SELECT * from dictionary_table WHERE is_searched = 1")
    fun getSearchedWords() : Flow<List<Dictionary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word : Dictionary)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSearchedWord(word : String)

    @Query("DELETE from dictionary_table WHERE is_saved = 1")
    suspend fun clearSavedWords()

    @Query("DELETE from dictionary_table WHERE is_searched = 1")
    suspend fun clearSearchedWords()

    @Delete
    suspend fun deleteSavedWord(word: List<Dictionary>)
}

