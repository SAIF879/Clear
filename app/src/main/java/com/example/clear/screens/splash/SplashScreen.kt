package com.example.clear.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.clear.R
import com.example.clear.navigation.NavGraphs
import com.example.clear.navigation.RootNavGraph
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {

    val splashAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ShowHeading(heading = "Simple..", fontSize =50)
            ShowAnimation(composition = splashAnimation)
        }

        }

       splashNavigation(coroutineScope =coroutineScope ,navController = navController)

    }



@Composable
fun ShowAnimation(composition : LottieComposition?){
LottieAnimation(composition = composition, modifier = Modifier.size(300.dp))
}
@Composable
fun ShowHeading(heading: String, fontSize: Int) {
    Text(
        text = heading,
        style = TextStyle(
            fontSize = fontSize.sp,
            color = TextWhite,
            textAlign = TextAlign.Center,
            fontFamily =FontFamilyClear.fontMedium
        )
    )
}

private fun splashNavigation(coroutineScope: CoroutineScope , navController: NavController){
  coroutineScope.launch {
      delay(1500)
      navController.navigate(NavGraphs.Introduction)
  }
}