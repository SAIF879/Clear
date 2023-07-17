package com.example.clear.screens.home.dictionary.util

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.data.DataOrException
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repository: DictionaryRepository) : ViewModel() {

    private val _searchWord  = MutableLiveData<String>()
    val searchWord : LiveData<String>
        get() =_searchWord
    fun setSearchWord(word : String) {
        _searchWord.value = word
    }

    suspend fun getWordDetails(word : String) : DataOrException<List<WordInfoDto> , Boolean , Exception>{
        return repository.getWordDetails(word)
    }

    //suspend fun getWeatherData(city: String, units: String) : DataOrException<Weather , Boolean , Exception>{
    //     return repository.getWeather(city  , units = units)
    // }



}

//    private val _phoneNumber = MutableLiveData<String>()
//    val phoneNumber: LiveData<String>
//        get() = _phoneNumber
//
//    fun setphoneNumber(number: String) {
//        _phoneNumber.value = number
//
//    }

