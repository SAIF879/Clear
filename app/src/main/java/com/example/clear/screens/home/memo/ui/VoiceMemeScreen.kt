package com.example.clear.screens.home.memo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.DeepBlue

@Composable
fun VoiceMemoScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxSize()) {
            Text(text = "voice memos" , color = Color.White , fontSize = 30.sp)

        }
    }
}