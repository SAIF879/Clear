package com.example.clear.screens.introduction

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
import androidx.navigation.NavController
import com.example.clear.navigation.IntroScreens
import com.example.clear.navigation.NavGraphs
import com.example.clear.utils.commonComponents.GenerateButton

@Composable
fun IntroTodoScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxSize()){
            Text(text = "here comes the 2nd intro" , color = Color.Black , fontSize = 30.sp)
            GenerateButton{
                navController.popBackStack()
                navController.navigate(NavGraphs.Authentication)
            }
    }
    }
}