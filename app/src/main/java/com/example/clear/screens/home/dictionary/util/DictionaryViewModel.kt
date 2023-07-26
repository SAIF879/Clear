package com.example.clear.screens.home.dictionary.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.data.DataOrException
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repository: DictionaryRepository) : ViewModel() {

    private val _savedWordList = MutableStateFlow<List<Dictionary>>(emptyList())
    val savedWordList = _savedWordList.asStateFlow()

    private val _searchedWordList = MutableStateFlow<List<Dictionary>>(emptyList())
    val searchedList = _searchedWordList.asStateFlow()

    private val _searchWord  = MutableLiveData<String>()
    val searchWord : LiveData<String>
        get() =_searchWord


    init {
        viewModelScope.launch(Dispatchers.IO) {
                repository.getSavedWords().distinctUntilChanged()
                    .collect{listOfSavedWords->
                        if (listOfSavedWords.isNullOrEmpty()) Log.d("noSave", "Empty List ")
                        else _savedWordList.value = listOfSavedWords
                    }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSearchedWords().distinctUntilChanged()
                .collect{listOfSearchedWords->
                    if (listOfSearchedWords.isNullOrEmpty()) Log.d("noSearched", "Empty List")
                    else _searchedWordList.value = listOfSearchedWords
                }
        }
    }

    fun setSearchWord(word : String)  {
        _searchWord.value = word
    }

    suspend fun getWordDetails(word : String) : DataOrException<List<WordInfoDto> , Boolean , Exception>{
        return repository.getWordDetails(word)
    }


    fun addSavedWord(word:Dictionary) = viewModelScope.launch {
        repository.addSavedWord(word = word)
    }

    fun addSearchedWord(word: Dictionary) = viewModelScope.launch {
        repository.addSearchedWord(word = word)

    }

    fun clearSavedWord() = viewModelScope.launch {
        repository.clearSavedWord()
        repository.getSavedWords().distinctUntilChanged().collect{
            _savedWordList.value = it
        }
    }

    fun clearSearchedWord() = viewModelScope.launch {
        repository.clearSearchedWord()
        repository.getSearchedWords().distinctUntilChanged().collect{
            _searchedWordList.value = it
        }
    }



    fun deleteSavedWord(word: Dictionary)=viewModelScope.launch {
        repository.deleteWord(word = word)
        _savedWordList.value = _savedWordList.value - word
    }

    fun deleteSearchedWord(word:Dictionary)  = viewModelScope.launch {
        repository.deleteWord(word = word)
    }



}
