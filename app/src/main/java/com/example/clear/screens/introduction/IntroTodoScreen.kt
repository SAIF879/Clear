package com.example.clear.screens.introduction


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.NavGraphs
import com.example.clear.screens.introduction.components.HeadingIntro
import com.example.clear.screens.introduction.components.SubContentIntro
import com.example.clear.screens.introduction.components.SwipeToContinueButton
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.fonts.FontFamilyClear

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
                   Spacer(modifier = Modifier.size(100.dp))
                   Column(modifier = Modifier.fillMaxSize()) {
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           verticalAlignment = Alignment.CenterVertically,
                           horizontalArrangement = Arrangement.End
                       ) {
                           Box(modifier = Modifier.background(Color.White)){
                               Text(text = "ELE" , style = TextStyle(fontFamily = FontFamilyClear.fontBold , fontSize = 100.sp ))
                           }
                       }
                       Spacer(modifier = Modifier.size(50.dp))
                       Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start) {
                         Box(modifier =Modifier.background(Color.White) ){
                             Text(text = "VATE" , style = TextStyle(fontFamily = FontFamilyClear.fontBold , fontSize = 100.sp ))
                         }
                       }
                   }
               }
            Box(modifier = Modifier.weight(1f)){
                Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center) {
                    HeadingIntro(heading = "\nproductivity.")
                    SubContentIntro(content = "Effortless task management with our Todo Solution. Expand vocabulary using the Dictionary feature.")
                    SwipeToContinueButton(text = "Swipe to continue", icon = Icons.Filled.ArrowForwardIos ){
                        navController.navigate(NavGraphs.Authentication)
                    }
                }
            }
        }
    }
}
