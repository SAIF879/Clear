package com.example.clear.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clear.room.model.Note
import retrofit2.http.DELETE

//dao access sqlite data
@Dao
interface NoteDataBaseDao {

    @Query("SELECT * from note_tbl")
    fun getNotes() : List<Note>

    @Query("SELECT * from note_tbl where id =:id")
    fun getNoteById(id:String) :Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note:Note)

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    fun updateNote(note:Note)

   @Query("DELETE from note_tbl")
   fun clearNotes()

   @DELETE
   fun deleteNote(note : Note)
}