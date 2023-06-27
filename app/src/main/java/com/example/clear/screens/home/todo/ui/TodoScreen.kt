package com.example.clear.screens.home.todo.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.room.model.TodoData
import com.example.clear.screens.home.todo.components.CreateTodo
import com.example.clear.screens.home.todo.components.TodoCard
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TodoScreen(){
    var content = remember{
        mutableStateOf("")
    }
    val list = TodoData().loadTodo()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column(modifier = Modifier.padding(10.dp)) {
            CurrentDay()
            Spacer(modifier = Modifier.size(20.dp))
            CreateTodo(content = content) {}
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(list) { todo ->
                    TodoCard(task = todo)
                    Spacer(modifier = Modifier.size(20.dp))
                }
                item{
                    Spacer(modifier = Modifier.size(80.dp))
                }
            }

        }
    }
}

@Composable
fun CurrentDay(){
    val day = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE" , Locale.getDefault()))
    Text(text = day ,   style = TextStyle(
        color = TextWhite,
        fontSize = 30.sp,
        fontFamily = FontFamilyClear.fontMedium
    )
    )
}

@Composable
fun CurrentTime(){

}
