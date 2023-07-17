package com.example.clear.screens.home.dictionary.ui

import android.provider.Telephony.Mms.Part
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clear.data.DataOrException
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import java.lang.Exception

// for now take up a random word and print its details then go for a word that is not in api and handle it
@Composable
fun SearchWordScreen(navController: NavController , viewModel: DictionaryViewModel = hiltViewModel()) {
    val weatherData = produceState<DataOrException<List<WordInfoDto> , Boolean , Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
        value = viewModel.getWordDetails("bank")
    }.value

    if (weatherData.loading==true) Box(modifier = Modifier.fillMaxSize().background(DeepBlue)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center) , color = Color.White)
    }
    else if (weatherData.data!==null){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)){
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)){
                item { SearchWordHeader() }
                item { Spacer(modifier = Modifier.size(10.dp)) }
                item { Word(wordInfoDto =weatherData?.data ) }
    }
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
fun WordWithPronunciation(word : String?){
Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Text(text =word.toString(), style = TextStyle(fontSize = 30.sp , fontFamily = FontFamilyClear.fontSemiBold , color = TextWhite))
    Spacer(modifier = Modifier.size(10.dp))
    Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "pronunciation_icon" , tint = Color.White)
}
}




@Composable
fun PartOfSpeech(word : String?){
    Text(text = word.toString() , style = TextStyle(fontSize = 25.sp , fontFamily = FontFamilyClear.fontSemiBold , color = Color.White)
    , modifier = Modifier.padding(0.dp , 10.dp))
}



@Composable
fun Word(wordInfoDto: List<WordInfoDto>?){
    Column() {
        wordInfoDto?.forEach{
            Spacer(modifier = Modifier.size(10.dp))
            WordWithPronunciation(word = it.word?:"No Such Word Present")
            Text(text = it.phonetic?:"no phonetic",  color = Color.White)
           it.meanings.forEach{
              PartOfSpeech(word = it.partOfSpeech?:"")
               it.definitions.forEachIndexed{i,d->
                   Text(text = "${i+1}.${d.definition?:"No defination"}",  color = Color.White)
                   Text(text = d.example?:"",  color = Color.White)
               }
           }
            Spacer(modifier = Modifier.size(20.dp))
            Divider(color = Color.White)
        }


    }
}
@Composable
fun showdata(viewModel: DictionaryViewModel) {
    val data = produceState<DataOrException<List<WordInfoDto>, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
    ){
        value = viewModel.getWordDetails("Death")
    }.value

    if (data.loading==true) CircularProgressIndicator()
    else if (data.data!==null){
        Text(text = "${data.data.toString()}" , color = Color.White)
    }
}