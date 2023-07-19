package com.example.clear.screens.introduction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.navigation.IntroScreens
import com.example.clear.screens.introduction.components.HeadingIntro
import com.example.clear.screens.introduction.components.IntroButton
import com.example.clear.screens.introduction.components.SubContentIntro
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun IntroNotesScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(modifier = Modifier.weight(2f)){
                Column( modifier = Modifier.fillMaxSize()) {
                    Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(0.dp)) {
                        Box(modifier = Modifier.weight(2f).height(200.dp).background(RedPink))
                        DecorationBox(icon = Icons.Filled.Description, backgroundColor = LightRed, modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f) )
                    }
                    AppNameWithBackGround(appName = stringResource(id = R.string.app_name))
                }
            }
            Box(modifier = Modifier.weight(1f))
            {
                Column(verticalArrangement = Arrangement.Center , modifier = Modifier.fillMaxSize()) {
                    HeadingIntro(heading ="Effortless Note-taking" )
                    SubContentIntro(content = "Capture and Organize Your Thoughts and Ideas with Ease.")
                    IntroButton(text = "Continue") {
                        navController.navigate(IntroScreens.IntroTodoScreen.route)
                    }
                }
            }
        }
    }
}





@Composable
fun DecorationBox(icon : ImageVector, backgroundColor : Color, modifier: Modifier = Modifier ){
    Box( modifier = modifier
        .background(color = backgroundColor)){
        Icon(imageVector =icon , contentDescription ="icon_dis" , tint = Color.White , modifier = Modifier
            .fillMaxSize()
            .padding(30.dp) )
    }
}

@Composable
fun AppNameWithBackGround(appName : String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(end = 50.dp)){
        Text(text = appName , style = TextStyle(fontFamily = FontFamilyClear.fontBold , fontSize = 100.sp , color = Color.Black))
    }
}