package com.example.clear.screens.home.note.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.components.AddImageButton
import com.example.clear.screens.home.note.components.CreateNoteContent
import com.example.clear.screens.home.note.components.ImageCard
import com.example.clear.screens.home.note.components.WordsCount
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.utils.commonComponents.ui.CircularButton
import com.example.clear.utils.commonComponents.ui.ShowAlertDialogBox
import com.example.clear.utils.commonComponents.ui.StatusBarColor
import com.example.clear.utils.constants.Constants
import com.example.clear.utils.fonts.FontFamilyClear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditNotesScreen(navController: NavController, noteViewModel: NoteViewModel) {

    

    val note = remember {
        mutableStateOf(Constants.noteDetailPlaceHolder)
    }

    val noteId = noteViewModel.noteId.observeAsState().value

    val editTitle = remember {
        mutableStateOf(note.value.title)
    }

    val editContent = remember {
        mutableStateOf(note.value.content)
    }

    val showDialogBox = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val color = remember {
        mutableStateOf(note.value.color)
    }

    var selectImageUris by rememberSaveable { mutableStateOf<List<Uri?>>(emptyList()) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uri ->
            selectImageUris = selectImageUris + uri
        }
    )





    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            val data = noteViewModel.getNoteById(noteId)
            note.value = data ?: Constants.noteDetailPlaceHolder
            editContent.value = note.value.content
            editTitle.value = note.value.title
            color.value = note.value.color
            selectImageUris = note.value.image
        }
    }
    
    StatusBarColor(color = Color(color.value))


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(color.value))
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
                WordsCount(content = editContent.value)
                CircularButton(icon = Icons.Filled.Upgrade) {
                    if (editTitle.value.isNotEmpty() && editContent.value.isNotEmpty()) {
                        noteViewModel.updateNote(
                            Note(
                                title = editTitle.value,
                                content = editContent.value,
                                id = note.value.id,
                                color = note.value.color,
                                image = selectImageUris

                            )
                        )
                        Toast.makeText(context, "update", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else Toast.makeText(context, "Empty fields ", Toast.LENGTH_SHORT).show()
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(8f)
            ) {
                item {
                    CreateNoteContent(
                        content = editTitle,
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
                        content = editContent,
                        placeholder = "Note",
                        fontFamily = FontFamilyClear.fontRegular,
                        fontSize = 18,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                    )

                }
            }
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color(color.value))
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(20.dp)
            ) {

                items(selectImageUris){imageUri ->
                    imageUri.let { uri->
                        ImageCard(selectedImageUri = uri)
                        Spacer(modifier = Modifier.size(30.dp))
                    }
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

            Divider(color = Color.Black)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                CircularButton(icon = Icons.Filled.Delete){
                    showDialogBox.value = !showDialogBox.value
                }
                ShowAlertDialogBox(
                    showDialogBox = showDialogBox ,
                    title = "Erase Data",
                    content = "Are you sure you want to erase all data" ,
                    confirmText = "Delete" ,
                    cancelString = "Cancel"
                ) {
                    showDialogBox.value = false
                    editContent.value = ""
                    selectImageUris = emptyList()

                }
            }

        }
    }
}




