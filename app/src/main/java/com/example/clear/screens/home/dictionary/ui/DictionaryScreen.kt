package com.example.clear.screens.home.dictionary.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.navigation.NavGraphs
import com.example.clear.room.model.Dictionary
import com.example.clear.screens.home.dictionary.components.DictionaryHeader
import com.example.clear.screens.home.dictionary.components.RemoveSearchHistory
import com.example.clear.screens.home.dictionary.components.SavedWordCard
import com.example.clear.screens.home.dictionary.components.SearchedWordCard
import com.example.clear.screens.home.dictionary.components.isWordInList
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ShowAlertDialogBox
import com.example.clear.utils.fonts.FontFamilyClear

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DictionaryScreen(navController: NavController, dictionaryViewModel : DictionaryViewModel ) {



    val text = remember {
        mutableStateOf("")
    }

    val active = remember {
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

                        val addWord = Dictionary(wordName = text.value, isSearched = true)
                        if (!isWordInList(searchedWordList, addWord)) {
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
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
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
                    confirmText = "Clear",
                    cancelString = "Cancel"
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
                        DictionaryHeader(savedWordList) {
                            showDeleteAlertBox.value = true
                        }
                        ShowAlertDialogBox(
                            showDialogBox = showDeleteAlertBox,
                            title = "Delete All Saved Words?",
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
                    items(savedWordList) {
                        SavedWordCard(word = it, viewModel = dictionaryViewModel) {
                            if (it.wordName.trim().isNotEmpty()) {
                                dictionaryViewModel.setSearchWord(
                                    it.wordName.trim()
                                )
                                val addWord = Dictionary(
                                    wordName = it.wordName,
                                    isSearched = true,
                                    isSaved = true
                                )
                                if (!isWordInList(searchedWordList, addWord)) {
                                    dictionaryViewModel.addSearchedWord(
                                        addWord,
                                    )
                                }
                                navController.navigate(NavGraphs.Dictionary)
                            } else return@SavedWordCard
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.size(80.dp))
                    }

                }
            }
        }
    }
}











