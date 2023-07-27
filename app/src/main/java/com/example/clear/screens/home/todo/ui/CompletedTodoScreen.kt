package com.example.clear.screens.home.todo.ui

import android.util.Log
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
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.screens.home.todo.components.CompletedTaskCard
import com.example.clear.screens.home.todo.components.CompletedTaskHeadline
import com.example.clear.screens.home.todo.components.CompletedTodoHeader
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ui.ShowAlertDialogBox
import com.example.clear.utils.commonComponents.ui.ShowEmptyAnimation
import com.example.clear.utils.commonComponents.ui.StatusBarColor

@Composable
fun CompletedTodoScreen(
    navController: NavController,
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    val completedTodoList = todoViewModel.completedTodoList.collectAsState().value
    val showDialogBox = remember { mutableStateOf(false) }

    StatusBarColor(color = DeepBlue)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            CompletedTodoHeader { navController.popBackStack() }
            Spacer(modifier = Modifier.size(30.dp))
            CompletedTaskHeadline(heading = stringResource(id = R.string.completed_heading) + "(${completedTodoList.size})") {
                showDialogBox.value = true
            }

            ShowAlertDialogBox(
                showDialogBox = showDialogBox,
                "Delete All Completed Tasks",
                "Are you sure you want to delete all completed Tasks?",
                "Delete",
                "Cancel"
            ) {
                todoViewModel.clearCompletedTodoList()
                showDialogBox.value = false
            }
            Divider()
            Spacer(modifier = Modifier.size(15.dp))
            if (completedTodoList.isEmpty()) {
                ShowEmptyAnimation(
                    animatedRes = R.raw.no_todo,
                    text = stringResource(id = R.string.no_completed_todo)
                )
            }
                else {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(completedTodoList) { todo ->
                            CompletedTaskCard(task = todo)
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









