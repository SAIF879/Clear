package com.example.clear.screens.home.dictionary.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun SearchWordScreen(navController: NavController , viewModel: DictionaryViewModel) {

    val searchWord = viewModel.searchWord.observeAsState().value

    val WordData = produceState<DataOrException<List<WordInfoDto> , Boolean , Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
        value = viewModel.getWordDetails(searchWord.toString())
    }.value

    if (WordData.loading==true) Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center) , color = Color.White)
    }
    else if (WordData.data!==null){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)){
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)){
                item { Text(text = searchWord ?: "no word like it" , color= Color.White) }
                item { SearchWordHeader() }
                item { Spacer(modifier = Modifier.size(10.dp)) }
                item { Word(wordInfoDto =WordData?.data ) }
    }
    }
}
    else if (WordData.data==null){
        Box(modifier = Modifier.fillMaxSize().background(DeepBlue)){
            Column() {
                Text(text = "NO SUCH WORD EXISTS IN THE DATABASE " , style = TextStyle(color= Color.White , fontFamily = FontFamilyClear.fontMedium , fontSize = 30.sp))
            }
        }
    }
}

@Composable
fun SearchWordHeader(){
Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
 Row(verticalAlignment = Alignment.CenterVertically) {
     Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = "back_arrow", tint = Color.White , modifier = Modifier.size(30.dp))
     Text(text = "Search" , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
 }
    
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
    Column {
        wordInfoDto?.forEach{ wordData ->
            Spacer(modifier = Modifier.size(10.dp))
            WordWithPronunciation(word = wordData.word?:"No Such Word Present")
            Text(text = wordData.phonetic?:"no phonetic",  color = Color.White)
           wordData.meanings.forEach{
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


