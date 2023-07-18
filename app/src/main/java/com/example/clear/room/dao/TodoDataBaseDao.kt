package com.example.clear.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clear.room.model.Todo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDataBaseDao {

    @Query("SELECT * from todo_table WHERE is_completed = 0")
    fun getTodo(): Flow<List<Todo>>

    @Query("SELECT * from todo_table WHERE is_completed = 1 ")
    fun getCompletedTodo() : Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("DELETE from todo_table WHERE is_completed = 0")
    suspend fun clearTodo()

    @Query("DELETE from todo_table WHERE is_completed = 1")
    suspend fun clearCompletedTodo()

    @Delete
    suspend fun deleteTodo(todo: Todo)


}

