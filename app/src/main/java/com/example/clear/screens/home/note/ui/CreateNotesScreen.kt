package com.example.clear.screens.home.note.ui


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
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
import com.example.clear.screens.home.note.components.AddImageButton
import com.example.clear.screens.home.note.components.ChosenColor
import com.example.clear.screens.home.note.components.CreateNoteContent
import com.example.clear.screens.home.note.components.ImageCard
import com.example.clear.screens.home.note.components.WordsCount
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.utils.commonComponents.ui.CircularButton
import com.example.clear.utils.commonComponents.ui.StatusBarColor
import com.example.clear.utils.fonts.FontFamilyClear
import kotlinx.coroutines.launch

@Composable
fun CreateNotesScreen(
    navController: NavController ,
                       noteViewModel: NoteViewModel
)

{

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

    var selectImageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uri ->
            selectImageUris = uri
        }
    )


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
                    .weight(5f)
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
                            .height(300.dp)
                    )
                }
            }

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(noteBackgroundAnimatable.value)
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(20.dp)
            ) {
                items(selectImageUris){
                    ImageCard(selectedImageUri = it)
                    Spacer(modifier = Modifier.size(30.dp))
                }
                item {
                   AddImageButton(icon = Icons.Filled.Image){
                       photoPickerLauncher.launch(
                           PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                       )
                   }
              }
            }
            Spacer(modifier = Modifier.size(30.dp))
            
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








