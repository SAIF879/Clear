package com.example.clear.utils.commonComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusBarColor(color: Color){
    val systemUiController = rememberSystemUiController()


        systemUiController.setSystemBarsColor(
            color = color
        )
}
