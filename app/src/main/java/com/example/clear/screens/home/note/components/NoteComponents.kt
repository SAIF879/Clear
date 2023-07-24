package com.example.clear.screens.home.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import dagger.hilt.android.lifecycle.HiltViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun NoteCard(note : Note   , viewModel: NoteViewModel= hiltViewModel() ,onclick : () -> Unit = {}){
    val isFavourite = remember {
        mutableStateOf(false)
    }

    val update = SwipeAction(
        onSwipe = {},
        icon = {Icon(
            imageVector = Icons.Filled.Update,
            contentDescription = "icon",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )},
        background = Color.Green

    )
    val delete = SwipeAction(
        onSwipe = {
                viewModel.removeNote(note)
        },
        icon = {Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "icon",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )},
        background = Color.Red

    )
    
    SwipeableActionsBox(startActions = listOf(update) , endActions = listOf(delete)) {
        Card(modifier = Modifier
            .clickable { onclick.invoke() }
            .height(200.dp)
            .fillMaxWidth()
            .padding(1.dp) , shape = RectangleShape) {
            Column(modifier = Modifier
                .padding(5.dp)
                .fillMaxSize() , verticalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Row(horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.CenterVertically , modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()) {
                        DecorationBar()
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp)
                    ) {

                        ShowEllipseTitle(title = note.title)

                        Icon(
                            imageVector = if (isFavourite.value) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.Favorite
                            }, contentDescription = "fav_note",
                            tint = if (isFavourite.value) LightRed else Color.Gray
                        )

                    }
//                    ShowEllipseContent(content = note.content)
                    EllipsizeText(text = note.content)
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) { ShowWordsCount(content = note.content)}
            }

        }
        
    }

}

@Composable
fun NotesCard(note : Note , viewModel: NoteViewModel , onclick: () -> Unit){
    val delete = SwipeAction(
        onSwipe = {
            viewModel.removeNote(note)
        },
        icon = {Icon(
            imageVector = Icons.Filled.DeleteForever,
            contentDescription = "icon",
            tint = Color.White,
            modifier = Modifier.padding(0.dp)
        )},
        background = RedPink

    )
    SwipeableActionsBox(endActions = listOf(delete) , swipeThreshold = 150.dp) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(note.color))
                .height(200.dp)
                .padding(10.dp)
                .clickable { onclick.invoke() }
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(note.color))) {
                Column(modifier = Modifier
                    .weight(2f)
                    .background(Color(note.color))) {
                    Text(
                        text = note.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamilyClear.fontMedium,
                            color = TextWhite
                        )
                    )
//                    ShowEllipseContent(content = note.content)
                    EllipsizeText(text = note.content)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
                    WordsCount(content = note.content)
                    SwipeToDeleteSign()
                }
            }
        }
    }
}

@Composable
fun WordsCount(content: String){
    val text = content.trim().split("\\s".toRegex())
Text(text ="${text.size}"+ if (text.size==1) " Word" else " Words" , style = TextStyle(fontSize =18.sp , fontFamily = FontFamilyClear.fontMedium  , color = TextWhite) , maxLines = 1)
}

@Composable
fun SwipeToDeleteSign(){
Row() {
    Icon(imageVector =Icons.Filled.Delete , contentDescription ="delete_icon" )
    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="back_arrow" )
}
}

@Composable
fun ShowWordsCount(content: String) {
    Box(modifier = Modifier
        .width(100.dp)
        .wrapContentHeight(), contentAlignment = Alignment.Center) {
        val text = content.trim().split("\\s+".toRegex())
        Text(
            text = "${text.size+1} " + if (text.size<=1) "Word" else "Words"  ,
            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontMedium),
            maxLines = 1
        )
    }
}

//@Composable
//fun ShowEllipseContent(content: String, maxWords: Int = 24, fontFamily: FontFamily = FontFamilyClear.fontRegular, fontSize : Int = 18) {
//    Text(
//        text = if (content.trim().split("\\s+".toRegex()).size > maxWords) "${content.trim().split("\\s+".toRegex()).take(maxWords)}........" else content,
//        style = TextStyle(fontSize = fontSize.sp, fontFamily = fontFamily)
//    )
//}

@Composable
fun EllipsizeText(text: String, maxLength: Int = 120) {
    val ellipsizedText = remember(text) {
        if (text.length > maxLength) {
            text.substring(0, maxLength - 3) + "..."
        } else {
            text
        }
    }

    Text(text = ellipsizedText , style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontRegular))
}

@Composable
fun ShowEllipseTitle(title: String, maxLength: Int = 9) {
    Text(
        text = if (title.length > maxLength) "${title.take(maxLength)}..." else title,
        style = TextStyle(fontSize = 15.sp, fontFamily = FontFamilyClear.fontBlack),
    )
}


@Composable
fun DecorationBar(){
    Spacer(modifier = Modifier
        .width(150.dp)
        .height(3.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(
            Color.Gray
        ))
    Spacer(modifier = Modifier.size(5.dp))
}

@Composable
fun ShowContentCount(content: String) {
    Text(
        text =  if (content.isEmpty() )"0 Word" else {"${content.trim().split("\\s+".toRegex()).size} Words" },
        style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontMedium),
        maxLines = 1
    )
}
@Composable
fun ColorCircle(color : Color , onClick : () -> Unit){
    Box(modifier = Modifier
        .size(50.dp)
        .border(2.dp, Color.Black, CircleShape)
        .background(color = color, shape = CircleShape)
        .aspectRatio(1f)
        .clickable {
            onClick.invoke()
        }
    )
}

@Composable
fun ChosenColor(modifier: Modifier, onSelectColor: (Color) -> Unit) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(Note.noteColors){
            ColorCircle(color = it) {
                onSelectColor.invoke(it)
            }
        }
    }
}






