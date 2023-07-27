package com.example.clear.screens.home.note.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.navigation.NavGraphs
import com.example.clear.navigation.NoteScreens
import com.example.clear.screens.home.note.components.LocalGreeting
import com.example.clear.screens.home.note.components.NotesCard
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.commonComponents.ShowEmptyAnimation
import com.example.clear.utils.commonComponents.StatusBarColor
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun NoteScreen(navController: NavController  , noteViewModel: NoteViewModel ) {

    val noteList = noteViewModel.noteList.collectAsState().value
    StatusBarColor(color = DeepBlue)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LocalGreeting()
                CircularButton(icon = Icons.Filled.Add) {
                    navController.navigate(NavGraphs.Note)
                }
            }
            Text(
                text = "My\nNotes",
                style = TextStyle(
                    fontFamily = FontFamilyClear.fontMedium,
                    fontSize = 50.sp,
                    color = Color.White
                ), modifier = Modifier.padding(5.dp)
            )

            if (noteList.isEmpty()) {
                ShowEmptyAnimation(
                    animatedRes = R.raw.empty_list,
                    text = "Currently, no notes are available for display."
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.dp)
                        )
                    }
                    items(noteList) {
                        NotesCard(note = it, viewModel = noteViewModel) {
                            noteViewModel.getNoteId(it.id)
                            navController.navigate(NoteScreens.EditNotesScreen.route)
                        }
                        Spacer(modifier = Modifier.size(5.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.size(80.dp))
                    }

                }


            }
        }
    }
}








