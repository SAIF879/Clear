package com.example.clear.screens.home.dictionary.ui



import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.clear.R
import com.example.clear.data.DataOrException
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.AnimatedLottie
import com.example.clear.utils.commonComponents.ShimmerAnimation
import com.example.clear.utils.fonts.FontFamilyClear
import java.lang.Exception


@Composable
fun SearchWordScreen(navController: NavController , viewModel: DictionaryViewModel) {



    val searchWord = viewModel.searchWord.observeAsState().value

    val isSaved = remember{
        mutableStateOf(false)
    }

    val wordData = produceState<DataOrException<List<WordInfoDto> , Boolean , Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
        value = viewModel.getWordDetails(searchWord.toString())
    }.value

    if (wordData.loading==true) Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)) {
        ShimmerAnimation()
    }
    else if (wordData.data!==null){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)){

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)){
                item { SearchWordHeader(isSaved = isSaved  , navController = navController ) }
                item { Spacer(modifier = Modifier.size(10.dp)) }
                item {  Word(wordInfoDto =wordData?.data  , isSaved = isSaved , viewModel = viewModel) }
    }
    }
} else if (wordData.data == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue)
        ) {
            Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
               AnimatedLottie(animationRes = R.raw.not_found)
                Text(
                    text = "NO SUCH WORD EXISTS IN THE DATABASE ",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamilyClear.fontMedium,
                        fontSize = 30.sp
                    ) , modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}



@Composable
fun SearchWordHeader(isSaved : MutableState<Boolean> , navController: NavController){
Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
 Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxSize()) {
Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = "back_arrow", tint = Color.White , modifier = Modifier
        .size(30.dp)
        .clickable {
            navController.popBackStack()
        })
    Text(text = "Search" , style = TextStyle(fontSize = 20.sp , fontFamily = FontFamilyClear.fontMedium , color = TextWhite))
}
     Row(verticalAlignment = Alignment.CenterVertically) {
         Icon(
             imageVector = if (!isSaved.value) Icons.Outlined.Remove else Icons.Filled.Bookmark,
             contentDescription = "icon_dis", tint = Color.White,
          modifier = Modifier
              .size(40.dp)
              .clickable {
                  isSaved.value = !isSaved.value
              }
         )
     }
 }
}
}

@Composable
fun WordWithPronunciation(word : String?){
Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Text(text =word.toString(), style = TextStyle(fontSize = 30.sp , fontFamily = FontFamilyClear.fontSemiBold , color = TextWhite))
    Spacer(modifier = Modifier.size(10.dp))
  //  Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "pronunciation_icon" , tint = Color.White)
}
}




@Composable
fun PartOfSpeech(word : String?){
    Text(text = word.toString() , style = TextStyle(fontSize = 25.sp , fontFamily = FontFamilyClear.fontSemiBold , color = Color.White)
    , modifier = Modifier.padding(0.dp , 10.dp))
}



@Composable
fun Word(wordInfoDto: List<WordInfoDto>?, isSaved : MutableState<Boolean> , viewModel: DictionaryViewModel ){
    Column {
        wordInfoDto?.forEach{ wordData ->
            Spacer(modifier = Modifier.size(10.dp))
            WordWithPronunciation(word = wordData.word?:"No Such Word Present")

            if (isSaved.value) viewModel.addSavedWord(Dictionary(wordName =  wordData.word?:"" , isSaved = true))

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


