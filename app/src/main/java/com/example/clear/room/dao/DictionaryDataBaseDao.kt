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

    @Query("SELECT * FROM dictionary_table WHERE word_name = :word")
    fun getWord(word: String): Flow<Dictionary?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word : Dictionary)


    @Query("DELETE from dictionary_table WHERE is_saved = 1")
    suspend fun clearSavedWords()

    @Query("DELETE from dictionary_table WHERE is_searched = 1")
    suspend fun clearSearchedWords()

//    @Query("SELECT EXISTS(SELECT 1 FROM dictionary_table WHERE is_saved = :isSaved AND word_name = :wordName)")
//    suspend fun checkExistence(isSaved:Boolean,wordName:String)

    @Delete
    suspend fun deleteSavedWord(word: List<Dictionary>)
}

