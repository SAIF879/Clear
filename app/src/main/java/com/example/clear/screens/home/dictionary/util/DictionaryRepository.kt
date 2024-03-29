package com.example.clear.screens.home.dictionary.util

import  android.util.Log
import com.example.clear.data.DataOrException
import com.example.clear.networkServices.CommonApiServices
import com.example.clear.room.dao.DictionaryDataBaseDao
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class DictionaryRepository @Inject constructor(
    private val api: CommonApiServices,
    private val dictionaryDataBaseDao: DictionaryDataBaseDao
) {

    suspend fun getWordDetails(word: String): DataOrException<List<WordInfoDto>, Boolean, Exception> {
        val response = try {
            Log.d("response", "getWordDetails: $word ")
            api.getWordDetails(word = word)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        Log.d("exception", "getWordDetails: $response")
        return DataOrException(data = response)

    }


    fun getSavedWords(): Flow<List<Dictionary>> =
        dictionaryDataBaseDao.getSavedWords().flowOn(Dispatchers.IO)
            .conflate()


    suspend fun addSearchedWord(word: Dictionary) {
        val searchedWords = dictionaryDataBaseDao.getSearchedWords().firstOrNull() ?: emptyList()
        val wordExists = searchedWords.any { it.wordName == word.wordName }

        if (!wordExists) {
            val searchedWord = word.copy(isSearched = true)
            dictionaryDataBaseDao.insertWord(word = searchedWord)
        }
    }

    suspend fun addSavedWord(word: Dictionary) {
        val savedWords = dictionaryDataBaseDao.getSavedWords().firstOrNull() ?: emptyList()
        val wordExists = savedWords.any { it.wordName == word.wordName }

        if (!wordExists) {
            val savedWord = word.copy(isSaved = true)
            dictionaryDataBaseDao.insertWord(word = savedWord)
        }
    }


    suspend fun clearSavedWord() = dictionaryDataBaseDao.clearSavedWords()

    suspend fun deleteWord(word: Dictionary) = dictionaryDataBaseDao.deleteWord(word = word)


    fun getSearchedWords(): Flow<List<Dictionary>> =
        dictionaryDataBaseDao.getSearchedWords().flowOn(Dispatchers.IO)
            .conflate()

    suspend fun clearSearchedWord() = dictionaryDataBaseDao.clearSearchedWords()


}



