package com.example.clear.screens.authentication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.navigation.AuthenticationScreens
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ShowHeading
import com.example.clear.utils.commonComponents.GenerateButtonWithIcon

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            ShowHeading(heading = stringResource(id = R.string.welcome), fontSize = 40)
            GenerateButtonWithIcon("Continue With Google" , R.drawable.google){
                navController.navigate(AuthenticationScreens.EnterDetailsScreen.route)
            }

        }
    }
}


