package com.example.clear.screens.home.dictionary.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clear.data.DataOrException
import com.example.clear.screens.home.dictionary.components.NoDataFound
import com.example.clear.screens.home.dictionary.components.SearchWordHeader
import com.example.clear.screens.home.dictionary.components.WordCard
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ShimmerAnimation
import java.lang.Exception


@Composable
fun SearchWordScreen(navController: NavController, viewModel: DictionaryViewModel) {


    val searchWord = viewModel.searchWord.observeAsState().value

    val isSaved = remember {
        mutableStateOf(false)
    }

    val wordData = produceState<DataOrException<List<WordInfoDto>, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getWordDetails(searchWord.toString())
    }.value

    if (wordData.loading == true) Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        ShimmerAnimation()
    }
    else if (wordData.data !== null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                item { SearchWordHeader(isSaved = isSaved, navController = navController) }
                item { Spacer(modifier = Modifier.size(10.dp)) }
                item {
                    WordCard(
                        wordInfoDto = wordData.data,
                        isSaved = isSaved,
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    else{
        NoDataFound(navController = navController)
    }
}






