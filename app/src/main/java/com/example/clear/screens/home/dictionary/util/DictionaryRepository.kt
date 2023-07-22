package com.example.clear.screens.home.dictionary.util

import android.util.Log
import com.example.clear.data.DataOrException
import com.example.clear.networkServices.CommonApiServices
import com.example.clear.room.dao.DictionaryDataBaseDao
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
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
        Log.d("excep", "getWordDetails: $response")
        return DataOrException(data = response)

    }


    fun getSavedWords() : Flow<List<Dictionary>> = dictionaryDataBaseDao.getSavedWords().flowOn(Dispatchers.IO)
        .conflate()

    suspend fun addSavedWord(word: Dictionary) = dictionaryDataBaseDao.insertWord(word = word)

    suspend fun addSearchedWord(word : Dictionary) = dictionaryDataBaseDao.insertWord(word = word)


    suspend fun clearSavedWord() = dictionaryDataBaseDao.clearSavedWords()

    suspend fun deleteWord(word:List<Dictionary>) = dictionaryDataBaseDao.deleteSavedWord(word = word)


    fun getSearchedWords() : Flow<List<Dictionary>> = dictionaryDataBaseDao.getSearchedWords().flowOn(Dispatchers.IO)
        .conflate()

    suspend fun clearSearchedWord() = dictionaryDataBaseDao.clearSearchedWords()



}



