package com.example.clear.screens.home.dictionary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DarkGray
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.AnimatedLottie
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.commonComponents.bounceClick
import com.example.clear.utils.fonts.FontFamilyClear
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun SavedWordCard(word: Dictionary, viewModel: DictionaryViewModel, onclick: () -> Unit) {

    val delete = SwipeAction(
        onSwipe = {
            viewModel.deleteSavedWord(word)
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier.padding(0.dp)
            )
        },
        background = RedPink

    )
    SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 100.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth().bounceClick()
                .padding(5.dp).clickable {
                    onclick.invoke()
                }
        ) {
            Text(
                text = word.wordName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamilyClear.fontRegular,
                    color = TextWhite
                ), modifier = Modifier
            )
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = "bookmark_icon",
                tint = Color.White,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun DictionaryHeader(savedWordList: List<Dictionary>, onclick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Saved\nWords(${savedWordList.size})",
            style = TextStyle(
                fontFamily = FontFamilyClear.fontMedium,
                fontSize = 30.sp,
                color = TextWhite
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        CircularButton(icon = Icons.Default.Delete) { onclick.invoke() }
    }
}

@Composable
fun SearchedWordCard(searchedWord: Dictionary) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp)
    ) {
        Icon(imageVector = Icons.Filled.History, contentDescription = "history_icon")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = searchedWord.wordName,
            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontRegular),
            color = DarkGray
        )
    }
}

@Composable
fun RemoveSearchHistory(text: String, onclick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onclick.invoke() }) {
        Text(
            text = text,
            style = TextStyle(fontFamily = FontFamilyClear.fontRegular, fontSize = 18.sp)
        )
    }
}

@Composable
fun WordName(word: String?) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = word.toString(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamilyClear.fontSemiBold,
                color = TextWhite
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}


@Composable
fun PartOfSpeech(word: String?) {
    Text(
        text = word.toString(),
        style = TextStyle(
            fontSize = 25.sp,
            fontFamily = FontFamilyClear.fontSemiBold,
            color = Color.White
        ),
        modifier = Modifier.padding(0.dp, 10.dp)
    )
}


@Composable
fun WordCard(
    wordInfoDto: List<WordInfoDto>?,
    isSaved: MutableState<Boolean>,
    viewModel: DictionaryViewModel
) {
    Column {
        wordInfoDto?.forEach { wordData ->
            Spacer(modifier = Modifier.size(10.dp))
            WordName(word = wordData.word ?: "No Such Word Present")

            if (isSaved.value) viewModel.addSavedWord(
                Dictionary(
                    wordName = wordData.word,
                    isSaved = true
                )
            )

            Text(text = wordData.phonetic ?: "no phonetic", color = Color.White)
            wordData.meanings.forEach {
                PartOfSpeech(word = it.partOfSpeech ?: "")
                it.definitions.forEachIndexed { i, d ->
                    Text(text = "${i + 1}.${d.definition ?: "No definition"}", color = Color.White)
                    Text(text = d.example ?: "", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Divider(color = Color.White)
        }


    }
}

@Composable
fun SearchWordHeader(isSaved: MutableState<Boolean>, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "back_arrow",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }

                        ) {
                            navController.popBackStack()
                        })
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamilyClear.fontMedium,
                        color = TextWhite
                    )
                )
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
fun NoDataFound(navController: NavController ,animatedRes : Int , text : String ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "back_arrow",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(

                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }

                        ) {
                            navController.popBackStack()
                        })
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamilyClear.fontMedium,
                        color = TextWhite
                    )
                )
            }


            AnimatedLottie(animationRes = animatedRes )
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamilyClear.fontMedium,
                    fontSize = 22.sp
                ), modifier = Modifier.padding(10.dp)
            )
        }
    }
}


fun isWordInList(wordList: List<Dictionary>, word: Dictionary): Boolean {
    return wordList.any { it.wordName == word.wordName }
}