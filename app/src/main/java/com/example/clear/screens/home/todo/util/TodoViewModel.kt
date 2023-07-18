package com.example.clear.screens.home.todo.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clear.room.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
     val todoList = _todoList.asStateFlow()

    private val _completedTodoList = MutableStateFlow<List<Todo>>(emptyList())
    val completedTodoList = _completedTodoList.asStateFlow()


    init {

        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodo().distinctUntilChanged()
                .collect{listOfTodo->
                    if (listOfTodo.isNullOrEmpty()) Log.d("todoEmpty", "Empty todo list ")
                    else _todoList.value = listOfTodo
                }
        }

        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getCompletedTodo().distinctUntilChanged()
                .collect{listOfCompletedTodo->
                    if (listOfCompletedTodo.isNullOrEmpty()) Log.d("todoEmpty", "Empty todo list ")
                    else _completedTodoList.value = listOfCompletedTodo
                }
        }
    }

    fun addTodo(todo: Todo) = viewModelScope.launch {
        val incompleteTodo = todo.copy(isCompleted = false)
        todoRepository.addTodo(todo  = incompleteTodo)
    }

    fun addCompletedTodo(todo: Todo) = viewModelScope.launch {
        val completedTodo = todo.copy(isCompleted = true )
        todoRepository.addTodo(todo = completedTodo)
    }

    fun removeTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.deleteTodo(todo = todo)
        _todoList.value = _todoList.value - todo
    }

    fun clearCompletedTodoList() = viewModelScope.launch {
        todoRepository.clearCompletedTodo()
        todoRepository.getCompletedTodo().distinctUntilChanged().collect(){
            _completedTodoList.value = it
        }
    }




}
