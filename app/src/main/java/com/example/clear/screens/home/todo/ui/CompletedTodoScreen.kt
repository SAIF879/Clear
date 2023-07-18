package com.example.clear.screens.home.todo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clear.screens.home.todo.components.TodoCard
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun CompletedTodoScreen(navController: NavController , todoViewModel : TodoViewModel = hiltViewModel()) {
    val list = todoViewModel.completedTodoList.collectAsState().value
Box(modifier = Modifier
    .fillMaxSize()
    .background(DeepBlue)){
    Column() {
        CompletedTodoHeader {}
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list) { todo ->
//                TodoCard(task = todo)
                Text(text = todo.content , color = Color.White)
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
fun CompletedTodoHeader(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back Arrow" , tint = Color.White , modifier = Modifier.clickable { onClick.invoke() })
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Todo", style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
        }
    }
}

//fun SearchWordHeader(){
//Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
// Row(verticalAlignment = Alignment.CenterVertically) {
//     Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = "back_arrow", tint = Color.White , modifier = Modifier.size(30.dp))
//     Text(text = "Search" , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
// }


