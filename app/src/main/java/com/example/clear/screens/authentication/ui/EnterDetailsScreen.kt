package com.example.clear.screens.authentication.ui

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
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.clear.navigation.NavGraphs
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.GenerateButton

@Composable
fun EnterDetailsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxSize().background(
            DeepBlue)
        )
        {
            Text(text = "Enter Details Screen" , color = Color.Black , fontSize = 30.sp)


        }
    }
}

