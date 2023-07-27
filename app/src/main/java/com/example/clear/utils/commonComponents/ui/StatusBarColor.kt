package com.example.clear.utils.commonComponents.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("SuspiciousIndentation")
@Composable
fun StatusBarColor(color: Color){
    val systemUiController = rememberSystemUiController()


        systemUiController.setSystemBarsColor(
            color = color
        )
}
