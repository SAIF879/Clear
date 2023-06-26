package com.example.clear.screens.home.note.util

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.clear.room.model.Note
import com.example.clear.room.model.NoteData

class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        //cause i want some data to be init when first called to check for data
        noteList.addAll(NoteData().loadNotes())
    }


    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note>{
     return noteList
    }


}