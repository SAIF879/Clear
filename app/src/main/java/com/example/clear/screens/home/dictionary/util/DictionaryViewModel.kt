package com.example.clear.screens.home.dictionary.util

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.data.DataOrException
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repository: DictionaryRepository) : ViewModel() {

    suspend fun getWordDetails(word : String) : DataOrException<List<WordInfoDto> , Boolean , Exception>{
        return repository.getWordDetails(word)
    }

}
