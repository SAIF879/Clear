package com.example.clear.screens.authentication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.NavGraphs
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.RedPink
import com.example.clear.utils.commonComponents.ClearButton
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun EnterDetailsScreen(navController: NavController) {

    val name = remember{
        mutableStateOf("")
    }
    val age = remember {
        mutableStateOf("")
    }

    val isRegistered = remember {
        mutableStateOf(false)
    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)) {

            Box(modifier = Modifier.size(100.dp).background(RedPink) , contentAlignment = Alignment.Center){
                Icon(imageVector = Icons.Filled.Add, contentDescription ="add_icon" , tint = Color.White , modifier = Modifier.fillMaxSize().padding(10.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Center

                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.background(RedOrange)) {
                            Text(
                                text = "Clear",
                                style = TextStyle(
                                    fontFamily = FontFamilyClear.fontMedium,
                                    fontSize = 100.sp,
                                    color = Color.Black
                                ), modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))


                    Box(modifier = Modifier.background(Color.White)) {
                        Text(
                            text = "About",
                            style = TextStyle(
                                fontFamily = FontFamilyClear.fontMedium,
                                fontSize = 50.sp,
                                color = Color.Black
                            ), modifier = Modifier.padding(5.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.background(RedOrange)) {
                            Text(
                                text = "You  ",
                                style = TextStyle(
                                    fontFamily = FontFamilyClear.fontMedium,
                                    fontSize = 50.sp,
                                    color = Color.White
                                ), modifier = Modifier.padding(5.dp)
                            )
                        }

               }
                    Spacer(modifier = Modifier.size(10.dp))
                    EnterDetails(
                        field = name,
                        placeHolder = "Enter Your Name",
                        label = "Name ",
                        icon = Icons.Filled.SentimentVerySatisfied,
                        modifier = Modifier
                    )
                    EnterDetails(
                        field = age,
                        placeHolder = "Enter Your Age",
                        label = "Age",
                        icon = Icons.Filled.ArrowOutward,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    ClearButton(text = "Continue") {
                            navController.navigate(NavGraphs.Bottom)
                    }
                }
            }

        }
    }
}

@Composable
fun EnterDetails(field: MutableState<String>, placeHolder  : String, label : String, icon : ImageVector  ,modifier: Modifier ) {
    OutlinedTextField(
        value = field.value,
        onValueChange = {field.value = it} ,
        textStyle = TextStyle(fontFamily = FontFamilyClear.fontRegular , fontSize = 15.sp),
        modifier= modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp, 10.dp),
                placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(
                    fontFamily = FontFamilyClear.fontRegular,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            )
        },
        trailingIcon = { Icon(imageVector = icon, contentDescription = "person_icon" , tint = Color.White)},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = RedPink,
                unfocusedIndicatorColor = RedPink,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedContainerColor = RedPink
            )
        )
}




