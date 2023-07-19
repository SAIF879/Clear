package com.example.clear.screens.introduction.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.IntroScreens
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun HeadingIntro(heading : String){
    Text(text = heading , style = TextStyle(fontSize = 40.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite ,), modifier = Modifier.padding(10.dp))
}

@Composable
fun SubContentIntro(content : String){
    Text(text = content , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium  , color = Color.Gray ) , modifier = Modifier.padding(10.dp))
}


@Composable
fun IntroButton(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = RedOrange),
        shape = RoundedCornerShape(0.dp) , modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp)) {
        Text(
            text = text,
            style = TextStyle(fontFamily = FontFamilyClear.fontBold, fontSize = 25.sp)
        )
    }
}


@Composable
fun SwipeToContinueButton(text: String , icon: ImageVector , navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(13.dp)
        .background(RedOrange, shape = RoundedCornerShape(30.dp)) ,){

        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            val update = SwipeAction(
                onSwipe = {navController.navigate(IntroScreens.IntroNotesScreen.route)},
                icon = {Icon(
                    imageVector = Icons.Filled.Update,
                    contentDescription = "icon",
                    tint = Color.Transparent,
                    modifier = Modifier.padding(0.dp)
                )},
                background = Color.Transparent

            )
            SwipeableActionsBox(startActions = listOf(update) , swipeThreshold = 150.dp , backgroundUntilSwipeThreshold = Color.Transparent ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp)
                        .background(RedPink, shape = CircleShape)
                        .aspectRatio(1f), contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = "icon_des", tint = Color.White)
                }

            }
            Spacer(modifier =Modifier.size(40.dp))
            Text(text =text , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium  , color = Color.White))
        }

    }
}

//fun NoteCard(note : Note   , viewModel: NoteViewModel= hiltViewModel() ,onclick : () -> Unit = {}){
//    val isFavourite = remember {
//        mutableStateOf(false)
//    }
//
//    val update = SwipeAction(
//        onSwipe = {},
//        icon = {Icon(
//            imageVector = Icons.Filled.Update,
//            contentDescription = "icon",
//            tint = Color.White,
//            modifier = Modifier.padding(16.dp)
//        )},
//        background = Color.Green
//
//    )
//    val delete = SwipeAction(
//        onSwipe = {
//                viewModel.removeNote(note)
//        },
//        icon = {Icon(
//            imageVector = Icons.Filled.Delete,
//            contentDescription = "icon",
//            tint = Color.White,
//            modifier = Modifier.padding(16.dp)
//        )},
//        background = Color.Red
//
//    )
//
//    SwipeableActionsBox(startActions = listOf(update) , endActions = listOf(delete)) {
//        Card(modifier = Modifier
//            .clickable { onclick.invoke() }
//            .height(200.dp)
//            .fillMaxWidth()
//            .padding(1.dp) , shape = RectangleShape) {
//            Column(modifier = Modifier
//                .padding(5.dp)
//                .fillMaxSize() , verticalArrangement = Arrangement.SpaceBetween) {
//                Column() {
//                    Row(horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.CenterVertically , modifier = Modifier
//                        .padding(5.dp)
//                        .fillMaxWidth()) {
//                        DecorationBar()
//                    }
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(1.dp)
//                    ) {
//
//                        ShowEllipseTitle(title = note.title)
//
//                        Icon(
//                            imageVector = if (isFavourite.value) {
//                                Icons.Filled.Favorite
//                            } else {
//                                Icons.Outlined.Favorite
//                            }, contentDescription = "fav_note",
//                            tint = if (isFavourite.value) LightRed else Color.Gray
//                        )
//
//                    }
//                    ShowEllipseContent(content = note.content)
//                }
//                Row(
//                    horizontalArrangement = Arrangement.End,
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.fillMaxWidth()
//                ) { ShowWordsCount(content = note.content)}
//            }
//
//        }
//
//    }
//
//}