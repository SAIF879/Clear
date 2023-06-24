package com.example.clear.screens.introduction

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.IntroScreens
import com.example.clear.navigation.NavGraphs
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.GenerateButton

@Composable
fun IntroTodoScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Mycanvas()

        }
    }
}

@Preview
@Composable
fun Mycanvas() {
    Canvas(
        modifier = Modifier
            .padding(5.dp)
            .size(300.dp),
    ) {
        drawRect(size = size, color = Color.Red)
        drawCircle(
            radius = 200f, center = Offset(750f, 650f), brush = Brush.radialGradient(
                center = center, radius = 100f, colors = listOf(
                    DeepBlue, DeepBlue
                )
            )
        )
        drawCircle(
            radius = 150f, center = Offset(750f , 650f ), brush = Brush.radialGradient(
                center = center, radius = 100f, colors = listOf(
                    Color.White, Color.White
                )
            )
        )
    }
}

//     GenerateButton {
//                navController.popBackStack()
//                navController.navigate(NavGraphs.Authentication)
//            }