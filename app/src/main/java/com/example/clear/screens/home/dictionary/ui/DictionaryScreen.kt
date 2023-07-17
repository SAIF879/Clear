package com.example.clear.screens.home.dictionary.ui

import android.annotation.SuppressLint
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.clear.data.DataOrException
import com.example.clear.navigation.NavGraphs
import com.example.clear.screens.home.dictionary.data.WordInfoDto
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DictionaryScreen(navController: NavController,viewModel : DictionaryViewModel = hiltViewModel()) {


    var text = remember {
        mutableStateOf("")
    }

    var active = remember {
        mutableStateOf(false)
    }

    var items = remember {
        mutableStateListOf(
            "saif",
            "saif", "saif"
        )
    }


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
                                items.add(text.value)
                                // active.value = false
                                text.value = ""
                                viewModel.setSearchWord(text.value.trim())
                                navController.navigate(NavGraphs.Dictionary)
                            }
                            else return@SearchBar
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

                        items.forEach {
                            Row(modifier = Modifier
                                .padding(end = 14.dp)
                                .clickable {
                                    navController.navigate(NavGraphs.Dictionary)
                                }) {
                                Icon(
                                    imageVector = Icons.Default.History,
                                    contentDescription = "history_icon"
                                )
                                Text(
                                    text = it,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamilyClear.fontRegular,
                                        color = Color.Gray
                                    )
                                )

                            }
                        }

                    }
                    Spacer(modifier = Modifier.size(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue)
                ) {
                   LazyColumn(modifier = Modifier.fillMaxSize()){
//                     item {   showdata(viewModel = viewModel) }
                       item {
                         Row(modifier = Modifier.fillMaxWidth()) {
                             Text(
                                 text = "BookMark",
                                 style = TextStyle(
                                     fontFamily = FontFamilyClear.fontMedium,
                                     fontSize = 20.sp,
                                     color = TextWhite
                                 )
                             )
                         //    Icon(imageVector = Icons.Filled.BookMa, contentDescription = )
                         }
                       }

                   //bookmarks

                   }
                }
            }
            }
        }






