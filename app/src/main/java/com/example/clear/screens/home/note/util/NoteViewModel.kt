package com.example.clear.screens.home.note.util

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.room.model.Note
import com.example.clear.room.model.NoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val notesRepository: NoteRepository) : ViewModel(){

    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()


    init {
        //cause i want some data to be init when first called to check for data
        //noteList.addAll(NoteData().loadNotes())

        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) Log.d("Empty", "Emtpy list")
                    else _noteList.value = listOfNotes
                }
        }

    }


     fun addNote(note: Note) = viewModelScope.launch {
        notesRepository.addNote(note)
    }


      fun removeNote(note : Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
          _noteList.value = _noteList.value -note
    }

     fun updateNote(note: Note) = viewModelScope.launch {
        notesRepository.updateNote(note)
    }

     fun clearAllNotes(note : Note) = viewModelScope.launch {
        notesRepository.clearAllNoteS()
    }

}