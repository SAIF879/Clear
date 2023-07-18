package com.example.clear.screens.home.todo.util


import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDataBase: TodoDataBaseDao) {

    suspend fun addTodo(todo: Todo) = todoDataBase.insertTodo(todo = todo)

    suspend fun deleteTodo(todo  : Todo) = todoDataBase.deleteTodo(todo = todo)

    suspend fun clearAllTodo() = todoDataBase.clearTodo()

    fun getAllTodo() : Flow<List<Todo>> = todoDataBase.getTodo().flowOn(Dispatchers.IO).conflate()

    suspend fun clearCompletedTodo() = todoDataBase.clearCompletedTodo()

    fun getCompletedTodo()  : Flow<List<Todo>> = todoDataBase.getCompletedTodo().flowOn(Dispatchers.IO).conflate()



}

