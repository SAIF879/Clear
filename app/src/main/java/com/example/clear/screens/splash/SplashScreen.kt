package com.example.clear.screens.splash

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.clear.navigation.NavGraphs
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.utils.commonComponents.GenerateButton
import com.example.clear.utils.fonts.FontFamilyClear

@Preview
@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



        }

        }
    }

@Composable
fun ShowAnimation(){

}

@Composable
fun ShowHeading(heading: String, fontSize: Int) {
    Text(
        text = "",
        style = TextStyle(
            fontSize = fontSize.sp,
            color = LightRed,
            textAlign = TextAlign.Center,
            fontFamily =FontFamilyClear.fontMedium
        )
    )
}
