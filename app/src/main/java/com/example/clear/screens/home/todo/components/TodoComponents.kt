package com.example.clear.screens.home.todo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clear.room.model.Todo
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.LightGreen2
import com.example.clear.ui.theme.LightGreen3
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedOrange
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.fonts.FontFamilyClear
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodoCard(task : Todo , viewModel : TodoViewModel = hiltViewModel() ) {
    val completedTask =SwipeAction(
        onSwipe = {
            task.isCompleted
                  viewModel.addCompletedTodo(Todo(content = task.content  , todoColor = task.todoColor))
            viewModel.removeTodo(task)
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        },
        background = LightGreen2

    )
    val deleteTask = SwipeAction(
        onSwipe = {viewModel.removeTodo(task)},
        icon = {Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "icon",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )},
        background = LightRed

    )

    SwipeableActionsBox(startActions = listOf(completedTask) , endActions = listOf(deleteTask) , swipeThreshold = 150.dp) {
        Card(
            shape = RectangleShape, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = Color(task.todoColor))
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
//                        .padding(5.dp)
                ) {
                    Text(
                        text = task.content,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamilyClear.fontRegular,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
      //                  .padding(5.dp)
                ) {
                    Text(
                        text = formatDate(task.timeStamp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamilyClear.fontBlack,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMMM", Locale.getDefault())
    return sdf.format(Date(timestamp))
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateTodo(content: MutableState<String> , onClick : ()-> Unit) {

        Card(
            shape = RectangleShape, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = RedOrange)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    textStyle = TextStyle(
                        fontFamily = FontFamilyClear.fontRegular,
                        fontSize = 18.sp,
                        color = Color.Gray
                    ), modifier = Modifier
                        .weight(2f)
                        .wrapContentHeight(),
                    placeholder = {
                        Text(
                            text = "Add a Task", style = TextStyle(
                                fontFamily = FontFamilyClear.fontRegular,
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        )
                    },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = RedOrange,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                CircularButton(icon = Icons.Filled.Add, size = 50) { onClick.invoke() }
            }
        }

}

@Composable
fun CompletedTaskCard(task: Todo) {
    Card(
        shape = RectangleShape, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = Color(task.todoColor))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.content,
                    style = TextStyle(
                        fontSize = 18.sp, fontFamily = FontFamilyClear.fontRegular,
                    ),
                )
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "completed_tasks",
                    tint = LightGreen3,
                modifier = Modifier.size(30.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                Text(
                    text = "22 december",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamilyClear.fontBlack,
                        color = Color.Black

                    )
                )
            }

        }
    }
}
