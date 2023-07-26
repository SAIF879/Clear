package com.example.clear.screens.home.note.ui


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.components.ChosenColor
import com.example.clear.screens.home.note.components.CreateNoteContent
import com.example.clear.screens.home.note.components.WordsCount
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.commonComponents.StatusBarColor
import com.example.clear.utils.fonts.FontFamilyClear
import kotlinx.coroutines.launch

@Composable
fun CreateNotesScreen( navController: NavController , noteViewModel: NoteViewModel   ) {

    val inputTitle = remember {
        mutableStateOf("")
    }

    val inputNote = remember {
        mutableStateOf(
            ""
        )
    }

    val noteColor = Note.noteColors[0]

    val noteBackgroundAnimatable = remember{
        Animatable(noteColor)
    }

    val selectedColor = remember { mutableStateOf(noteColor) }

    val scope = rememberCoroutineScope()
    
    StatusBarColor(color = noteBackgroundAnimatable.value)




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(noteBackgroundAnimatable.value)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                WordsCount(content = inputNote.value)
                CircularButton(icon = Icons.Filled.Add) {
                    if (inputTitle.value.isNotEmpty() && inputNote.value.isNotEmpty()) {
                        noteViewModel.addNote(
                            Note(
                                title = inputTitle.value,
                                content = inputNote.value,
                                color = selectedColor.value.toArgb()
                            )
                        )
                        inputTitle.value = ""
                        inputNote.value = ""
                        navController.popBackStack()

                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth()
            ) {
                item {
                    CreateNoteContent(
                        content = inputTitle,
                        placeholder = "Title",
                        fontFamily = FontFamilyClear.fontMedium,
                        fontSize = 25,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    )
                }
                item {
                    CreateNoteContent(
                        content = inputNote,
                        placeholder = "Note",
                        fontFamily = FontFamilyClear.fontRegular,
                        fontSize = 18,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                }
            }
            Divider(color= Color.Black)
            ChosenColor( modifier = Modifier.weight(1f)){
                scope.launch {
                    noteBackgroundAnimatable.animateTo(
                        targetValue = it,
                        animationSpec = tween(durationMillis = 200)
                    )
                    selectedColor.value = it
                }

            }
        }
    }

}





