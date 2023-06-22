package com.example.clear.screens.home.note.util


import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDataBaseDao: NoteDataBaseDao) {
    suspend fun addNote(note : Note) = noteDataBaseDao.insertNote(note)

    suspend fun updateNote(note:Note) = noteDataBaseDao.updateNote(note)

    suspend fun deleteNote(note:Note) = noteDataBaseDao.deleteNote(note)

    suspend fun clearAllNoteS() = noteDataBaseDao.clearNotes()

    fun getAllNotes() : Flow<List<Note>> = noteDataBaseDao.getNotes().flowOn(Dispatchers.IO)
        .conflate()

}