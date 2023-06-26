package com.example.clear.screens.home.note.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clear.navigation.NavGraphs
import com.example.clear.room.model.Note
import com.example.clear.room.model.NoteData
import com.example.clear.screens.home.note.components.NoteCard
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteScreen(notes : List<Note> ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LocalGreetingWithName(name = "Sam")
                CircularButton(Icons.Filled.Add) {
                    //navController.navigate(NavGraphs.Note)
               }
            }
            //local heading
            Text(
                text = "My\nNotes",
                style = TextStyle(
                    fontFamily = FontFamilyClear.fontMedium,
                    fontSize = 50.sp,
                    color = Color.White
                ), modifier = Modifier.padding(5.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxWidth()) {
                item { 
                    Spacer(modifier = Modifier.fillMaxWidth().height(100.dp))
                }
                items(notes){
                  NoteCard(note = it)
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }


        }
    }
}

@Composable
fun LocalGreetingWithName(name: String) {
    Text(
        text = localGreeting() + ", $name",
        style = TextStyle(
            color = TextWhite,
            fontSize = 20.sp,
            fontFamily = FontFamilyClear.fontMedium
        )
    )
}

 fun localGreeting() : String{
    val currentTime = Date()
    val timeFormat = SimpleDateFormat("HH", Locale.getDefault())
    val hour = timeFormat.format(currentTime).toInt()

    return when(hour){
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good AfterNoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}


@Composable
fun CircularButton(icon : ImageVector,onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(LightRed, shape = CircleShape)
            .aspectRatio(1f)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = " icon",
            tint = Color.White,
            modifier = Modifier.fillMaxSize()
        )
    }
}



