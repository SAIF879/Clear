package com.example.clear.screens.home.todo.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.clear.R
import com.example.clear.navigation.NavGraphs
import com.example.clear.room.model.Todo
import com.example.clear.screens.home.todo.components.CreateTodo
import com.example.clear.screens.home.todo.components.TodoCard
import com.example.clear.screens.home.todo.components.TodoHeader
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ui.ShowEmptyAnimation
import com.example.clear.utils.commonComponents.ui.StatusBarColor


@Composable
fun TodoScreen(todoViewModel: TodoViewModel = hiltViewModel(), navController: NavHostController) {
    val content = remember {
        mutableStateOf("")
    }
    StatusBarColor(color = DeepBlue)
    val context = LocalContext.current
    val todoList = todoViewModel.todoList.collectAsState().value
    val timestamp = System.currentTimeMillis()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            TodoHeader {
                navController.navigate(NavGraphs.Todo)
            }
            Spacer(modifier = Modifier.size(10.dp))
            CreateTodo(content = content) {
                if (content.value.isNotEmpty()) {

                    todoViewModel.addTodo(
                        Todo(
                            content = content.value,
                            timeStamp = timestamp,
                            todoColor = Todo.todoColors.random().toArgb()
                        )
                    )
                    content.value = ""

                } else {
                    Toast.makeText(context, "empty ", Toast.LENGTH_SHORT).show()
                }
            }
            Spacer(modifier = Modifier.size(20.dp))


            if (todoList.isEmpty()) {
                ShowEmptyAnimation(animatedRes = R.raw.no_todo, text = "No Due Tasks to Look At")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(todoList) { todo ->
                        TodoCard(task = todo)
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.size(80.dp))
                    }
                }

            }
        }
    }
}



