package com.example.clear.screens.home.note.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.room.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val notesRepository: NoteRepository) : ViewModel(){

    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _noteId  = MutableLiveData<UUID>()
    val noteId : LiveData<UUID>
        get() =_noteId


    fun getNoteId(id:UUID){
        _noteId.value = id
    }


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

    suspend fun getNoteById(noteId : UUID?) : Note?{
        return notesRepository.getNoteById(noteId = noteId)
    }

}