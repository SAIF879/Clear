package com.example.clear.screens.home.todo.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clear.screens.home.todo.components.CompletedTaskCard
import com.example.clear.screens.home.todo.util.TodoViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.OrangeYellow3
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun CompletedTodoScreen(navController: NavController , todoViewModel : TodoViewModel = hiltViewModel()) {
    val list = todoViewModel.completedTodoList.collectAsState().value
    val showDialogBox = remember{ mutableStateOf(false)}
Box(modifier = Modifier
    .fillMaxSize()
    .background(DeepBlue)){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        CompletedTodoHeader {navController.popBackStack()}
        Spacer(modifier = Modifier.size(30.dp))
        CompletedTaskHeadline(heading = "Completed\nTasks (${list.size})"){
            showDialogBox.value = true
            Log.d("DONE", "CompletedTodoScreen: $showDialogBox")
        }
        ShowAlertDialogBox(showDialogBox =showDialogBox ) {
            todoViewModel.clearCompletedTodoList()
            showDialogBox.value = false
        }
        Divider()
        Spacer(modifier = Modifier.size(15.dp))
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list) { todo ->
               CompletedTaskCard(task = todo)
                Spacer(modifier = Modifier.size(20.dp))
            }
            item{
                Spacer(modifier = Modifier.size(80.dp))
            }
        }
    }
}
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAlertDialogBox(showDialogBox: MutableState<Boolean> , onClick: () -> Unit) {
   if (showDialogBox.value) {
       AlertDialog(
           onDismissRequest = {showDialogBox.value = false },
           title = { Text(text="Delete All Completed Tasks" , style = TextStyle(fontFamily = FontFamilyClear.fontSemiBold , fontSize = 18.sp))  },
           text = { Text("Are you sure you want to delete all completed Tasks?", style = TextStyle(fontFamily = FontFamilyClear.fontRegular , fontSize = 15.sp)) },
           confirmButton = {
               Button(colors = ButtonDefaults.buttonColors(containerColor = LightRed),
                   onClick = {
                       onClick.invoke()
                   }
               ) {
                   Text("Delete", style = TextStyle(fontFamily = FontFamilyClear.fontMedium , fontSize = 15.sp))
               }
           },
           dismissButton = {
               Button(
                   colors = ButtonDefaults.buttonColors(containerColor = OrangeYellow3),
                   onClick = { showDialogBox.value=false}
               ) {
                   Text("Cancel",  style = TextStyle(fontFamily = FontFamilyClear.fontRegular , fontSize = 15.sp))
               }
           }
       )
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
            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back Arrow" , tint = Color.White , modifier = Modifier
                .size(30.dp)
                .clickable { onClick.invoke() })
            Text(text = "Todo", style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
        }
    }
}



@Composable
fun CompletedTaskHeadline(heading : String  , onClick: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = heading,
            style = TextStyle(
                fontFamily = FontFamilyClear.fontMedium,
                fontSize = 30.sp,
                color = Color.White
            ), modifier = Modifier.padding(5.dp)
        )
        
        CircularButton(icon = Icons.Filled.Delete){onClick.invoke()}

    }
}





