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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.navigation.IntroScreens
import com.example.clear.screens.introduction.components.AppNameWithBackGround
import com.example.clear.screens.introduction.components.DecorationBox
import com.example.clear.screens.introduction.components.HeadingIntro
import com.example.clear.screens.introduction.components.SubContentIntro
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedPink
import com.example.clear.utils.commonComponents.ui.ClearButton
import com.example.clear.utils.commonComponents.ui.StatusBarColor

@Composable
fun IntroNotesScreen(navController: NavController) {

    StatusBarColor(color = DeepBlue)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(modifier = Modifier.weight(2f)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        Box(modifier = Modifier
                            .weight(2f)
                            .height(200.dp)
                            .background(RedPink))
                        DecorationBox(
                            icon = Icons.Filled.Description,
                            backgroundColor = LightRed,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )
                    }
                    AppNameWithBackGround(appName = stringResource(id = R.string.app_name))
                }
            }
            Box(modifier = Modifier.weight(1f))
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    HeadingIntro(heading = "Effortless Note-taking")
                    SubContentIntro(content = "Capture and Organize Your Thoughts and Ideas with Ease.")
                    ClearButton(text = "Continue") {
                        navController.navigate(IntroScreens.IntroTodoScreen.route)
                    }
                }
            }
        }
    }
}
