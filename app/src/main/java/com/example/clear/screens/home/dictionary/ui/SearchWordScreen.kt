package com.example.clear.screens.home.dictionary.ui

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear

@Preview
@Composable
fun SearchWordScreen(){
Box(modifier = Modifier
    .fillMaxSize()
    .background(DeepBlue)){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        item { SearchWordHeader() }
        item { Spacer(modifier = Modifier.size(30.dp)) }
        item { WordWithPronunciation()  }
        item { Divider() }
        item { Spacer(modifier = Modifier.size(10.dp)) }
        item { WordDefinitions() }
    }

}
}


@Composable
fun SearchWordHeader(){
Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically) {
    Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = "back_arrow", tint = Color.White , modifier = Modifier.size(30.dp))
    Text(text = "Search" , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
}
}

@Composable
fun WordWithPronunciation(){
Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Text(text = "Clear" , style = TextStyle(fontSize = 30.sp , fontFamily = FontFamilyClear.fontSemiBold , color = TextWhite))
    Spacer(modifier = Modifier.size(10.dp))
    Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "pronunciation_icon" , tint = Color.White)
}
}


//heading with list size and list items
@Composable
fun WordDefinitions(){
Column(Modifier.fillMaxWidth()) {
    Row(Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Definitions" , style = TextStyle(fontFamily = FontFamilyClear.fontMedium , color = TextWhite, fontSize = 20.sp) )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "5" , style = TextStyle(fontFamily = FontFamilyClear.fontMedium , color = TextWhite , fontSize = 15.sp))
    }
}
}

@Composable
fun WordEtymology(){

}

@Composable
fun WordUse(){

}