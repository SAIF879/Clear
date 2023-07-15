package com.example.clear.screens.home.dictionary.ui

import android.annotation.SuppressLint
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.fonts.FontFamilyClear

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DictionaryScreen() {


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


            Scaffold {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue)
                ) {
                    SearchBar(
                        query = text.value,
                        onQueryChange = { text.value = it },
                        onSearch = {
                            items.add(text.value)
                            // active.value = false
                            text.value = ""
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
                            Row(modifier = Modifier.padding(end = 14.dp)) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue)
                ) {
                  
                    //pass the ui here
                }
            }
            }

        }



