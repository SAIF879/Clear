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

    init {

        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodo().distinctUntilChanged()
                .collect{listOfTodo->
                    if (listOfTodo.isNullOrEmpty()) Log.d("todoEmpty", "Empty todo list ")
                    else _todoList.value = listOfTodo
                }
        }
    }

    fun addTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.addTodo(todo  = todo)
    }

    fun removeTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.deleteTodo(todo = todo)
        _todoList.value = _todoList.value - todo
    }


}
