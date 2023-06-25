package com.example.clear.screens.home.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.LightRed
import com.example.clear.utils.fonts.FontFamilyClear
@Preview
@Composable
fun NoteCard(title : String = "sadsadsadsa" , content : String ="an apple a day keeps a doctor away an apple a day keeps a doctor away an apple a day keeps a doctor away fgfgf fgfg fgfg" , onclick : () -> Unit = {}){
    val isFavourite = remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .clickable { onclick.invoke() }
        .height(150.dp)
        .width(300.dp)) {
        Column(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize() , verticalArrangement = Arrangement.SpaceBetween) {
            Column() {
               Row(horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.CenterVertically , modifier = Modifier
                   .padding(3.dp)
                   .fillMaxWidth()) {
                DecorationBar()
               }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                ShowEllipseTitle(title = title)

                Icon(
                    imageVector = if (isFavourite.value) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.Favorite
                    }, contentDescription = "fav_note",
                    tint = if (isFavourite.value) LightRed else Color.Gray
                )

            }
            ShowEllipseContent(content = content)
        }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) { ShowWordsCount(content = content)}
        }

    }
}



@Composable
fun ShowWordsCount(content: String) {
    Box(modifier = Modifier
        .width(100.dp)
        .wrapContentHeight(), contentAlignment = Alignment.Center) {
        Text(
            text = "${content.trim().split("\\s+".toRegex()).size} Words",
            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontMedium),
            maxLines = 1
        )
    }
}

@Composable
fun ShowEllipseContent(content: String, maxWords: Int = 24, fontFamily: FontFamily = FontFamilyClear.fontRegular, fontSize : Int = 12) {
    Text(
        text = if (content.trim().split("\\s+".toRegex()).size > maxWords) "${content.trim().split("\\s+".toRegex()).take(maxWords)}..." else content,
        style = TextStyle(fontSize = fontSize.sp, fontFamily = fontFamily)
    )
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






