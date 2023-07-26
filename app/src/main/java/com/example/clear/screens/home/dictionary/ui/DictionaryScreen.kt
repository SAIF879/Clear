package com.example.clear.screens.home.dictionary.ui

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.NavGraphs
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DarkGray
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.Purple80
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.commonComponents.ShowAlertDialogBox
import com.example.clear.utils.commonComponents.bounceClick
import com.example.clear.utils.fonts.FontFamilyClear
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DictionaryScreen(navController: NavController, dictionaryViewModel : DictionaryViewModel ) {



    var text = remember {
        mutableStateOf("")
    }

    var active = remember {
        mutableStateOf(false)
    }

    val showDeleteAlertBox = remember{
        mutableStateOf(false)
    }

    val showDialogBox = remember{
        mutableStateOf(false)
    }



    val searchedWordList = dictionaryViewModel.searchedList.collectAsState().value
    val savedWordList = dictionaryViewModel.savedWordList.collectAsState().value



    Scaffold(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue)
                .padding(10.dp)
        ) {
            SearchBar(
                query = text.value,
                onQueryChange = { text.value = it },
                onSearch = {
                    if (text.value.trim().isNotEmpty()) {
                        dictionaryViewModel.setSearchWord(
                            text.value.replace(Regex("[^A-Za-z]"), "").trim()
                        )

                        val addWord = Dictionary(wordName = text.value , isSearched = true)
                      if (!isWordInList(searchedWordList , addWord)){
                          dictionaryViewModel.addSearchedWord(
                              addWord,
                          )
                      }
                        text.value = ""
                        navController.navigate(NavGraphs.Dictionary)
                    } else return@SearchBar
                },
                active = active.value,
                onActiveChange = { active.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Search..",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamilyClear.fontRegular,
                            color = Color.Gray
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search_icon"
                    )
                },
                trailingIcon = {
                    if (active.value)
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close_icon",
                            modifier = Modifier.clickable {
                                if (text.value.isNotEmpty()) text.value = ""
                                else active.value = false
                            })
                }
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                searchedWordList.forEach {
                    Row(modifier = Modifier
                        .padding(end = 14.dp)
                        .clickable {
                            dictionaryViewModel.setSearchWord(
                                it.wordName
                                    .replace(
                                        Regex("[^A-Za-z]"),
                                        ""
                                    )
                                    .trim()
                            )
                            navController.navigate(NavGraphs.Dictionary)
                            text.value = ""
                        }) {
                        SearchedWordCard(searchedWord = it)
                    }
                }
                RemoveSearchHistory(text = "clear History") {
                    showDialogBox.value = true
                }

                ShowAlertDialogBox(
                    showDialogBox = showDialogBox,
                    title = "clear search history?",
                    content = "Are you sure you want  to clear the search history",
                    confirmText ="Clear",
                    cancelString ="Cancel"
                ) {
                    dictionaryViewModel.clearSearchedWord()
                    showDialogBox.value = false
                }

            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DeepBlue)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                    DictionaryHeader(savedWordList){
                        showDeleteAlertBox.value = true
                    }
                        ShowAlertDialogBox(
                            showDialogBox = showDeleteAlertBox,
                            title = "Delete All Saved Words?" ,
                            content = "Are you sure you want to Delete all saved words ?",
                            confirmText = "Delete",
                            cancelString = "Cancel"
                        ) {
                            dictionaryViewModel.clearSavedWord()
                            showDeleteAlertBox.value = false

                        }
                    }
                    item { Divider() }
                    item { Spacer(modifier = Modifier.size(10.dp)) }
                    items(savedWordList){
                        SavedWordCard(word = it , viewModel =  dictionaryViewModel){
                            if (it.wordName.trim().isNotEmpty()) {
                                dictionaryViewModel.setSearchWord(
                                    it.wordName.trim()
                                )
                                val addWord = Dictionary(wordName = it.wordName , isSearched = true , isSaved = true)
                                if (!isWordInList(searchedWordList , addWord)){
                                    dictionaryViewModel.addSearchedWord(
                                        addWord,
                                    )
                                }
                                navController.navigate(NavGraphs.Dictionary)
                            } else return@SavedWordCard
                        }
                    }
                    item{
                        Spacer(modifier = Modifier.size(80.dp ))
                    }


                }
            }
        }
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

fun isWordInList(wordList: List<Dictionary>, word: Dictionary): Boolean {
    return wordList.any { it.wordName == word.wordName }
}

@Composable
fun SavedWordCard(word: Dictionary ,viewModel: DictionaryViewModel, onclick: () -> Unit) {

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






