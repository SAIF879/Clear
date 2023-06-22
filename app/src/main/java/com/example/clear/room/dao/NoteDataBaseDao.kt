package com.example.clear.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clear.room.model.Note
import kotlinx.coroutines.flow.Flow

//dao access sqlite data
@Dao
interface NoteDataBaseDao {

    @Query("SELECT * from note_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from note_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE from note_tbl")
    suspend fun clearNotes()

    @Delete
    suspend fun deleteNote(note: Note)

}