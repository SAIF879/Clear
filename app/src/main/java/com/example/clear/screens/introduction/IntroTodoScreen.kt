package com.example.clear.screens.introduction


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowLeft
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
import com.example.clear.screens.introduction.components.HeadingIntro
import com.example.clear.screens.introduction.components.IntroButton
import com.example.clear.screens.introduction.components.SubContentIntro
import com.example.clear.screens.introduction.components.SwipeToContinueButton
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.GenerateButton

@Composable
fun IntroTodoScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
               Box(modifier = Modifier.weight(1f)){
                   Column(modifier = Modifier.fillMaxSize()) {

                   }
               }
            Box(modifier = Modifier.weight(1f)){
                Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center) {
                    HeadingIntro(heading = "Elevate\nproductivity.")
                    SubContentIntro(content = "Effortless task management with our Todo Solution. Expand vocabulary using the Dictionary feature.")
                    SwipeToContinueButton(text = "Swipe to continue", icon = Icons.Filled.ArrowForwardIos ){
                        navController.navigate(NavGraphs.Authentication)
                    }
                }
            }
        }
    }
}
