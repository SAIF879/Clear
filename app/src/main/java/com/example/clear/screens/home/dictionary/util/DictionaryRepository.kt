package com.example.clear.screens.home.dictionary.util

import android.util.Log
import com.example.clear.data.DataOrException
import com.example.clear.networkServices.CommonApiServices
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import java.lang.Exception
import javax.inject.Inject

class DictionaryRepository  @Inject constructor(private val api : CommonApiServices) {

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
}






