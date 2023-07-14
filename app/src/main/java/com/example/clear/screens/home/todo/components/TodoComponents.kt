package com.example.clear.screens.home.todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clear.room.model.Todo
import com.example.clear.room.model.TodoData
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.Beige1
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.fonts.FontFamilyClear
import dagger.hilt.android.lifecycle.HiltViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun TodoCard(task : Todo , viewModel : TodoViewModel = hiltViewModel()) {

    val completedTask =SwipeAction(
        onSwipe = {},
        icon = {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        },
        background = Color.Green

    )
    val deleteTask = SwipeAction(
        onSwipe = {viewModel.removeTodo(task)},
        icon = {Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "icon",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )},
        background = Color.Red

    )

    SwipeableActionsBox(startActions = listOf(completedTask) , endActions = listOf(deleteTask)) {
        Card(
            shape = RectangleShape, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = RedOrange)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
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
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateTodo(content: MutableState<String> , onClick : ()-> Unit) {

        Card(
            shape = RectangleShape, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = RedOrange)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
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