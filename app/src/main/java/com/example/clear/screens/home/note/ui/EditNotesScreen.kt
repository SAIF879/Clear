package com.example.clear.screens.home.note.ui

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.components.ChosenColor
import com.example.clear.screens.home.note.components.ShowContentCount
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedOrange
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.constants.Constants
import com.example.clear.utils.fonts.FontFamilyClear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditNotesScreen(navController: NavController , noteViewModel: NoteViewModel ) {

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


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val color =  remember{
        mutableStateOf(note.value.color)
    }





    LaunchedEffect(true){
        scope.launch(Dispatchers.IO) {
           val data = noteViewModel.getNoteById(noteId)
            note.value = data ?: Constants.noteDetailPlaceHolder
            editContent.value = note.value.content
            editTitle.value = note.value.title
            color.value = note.value.color
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color =  Color(color.value))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth().weight(1f)
            ) {
                ShowContentCount(content = editContent.value)
                CircularButton(icon = Icons.Filled.Upgrade) {
                 if (editTitle.value.isNotEmpty() && editContent.value.isNotEmpty()){
                     noteViewModel.updateNote(
                         Note(title = editTitle.value , content = editContent.value , id = note.value.id , color = note.value.color)
                     )
                     Toast.makeText(context , "update" , Toast.LENGTH_SHORT).show()
                     navController.popBackStack()
                 }
                    else Toast.makeText(context , "Empty feilds ", Toast.LENGTH_SHORT).show()
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
                //content
                item {
                    CreateNoteContent(
                        content = editContent,
                        placeholder = "Note",
                        fontFamily = FontFamilyClear.fontRegular,
                        fontSize = 18,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )

                    //create bottom bar of colors that change background color and depending on it changes create note color
                    //  ChooseColor()
                }
            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditNoteContent(content : MutableState<String> , placeHolder : String , fontFamily: androidx.compose.ui.text.font.FontFamily , fontSize : Int , modifier: Modifier){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = content.value,
        onValueChange = { content.value = it },
        textStyle = TextStyle(fontFamily = fontFamily, fontSize = fontSize.sp),
        modifier = modifier,
        placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(
                    fontFamily = FontFamilyClear.fontRegular,
                    fontSize = fontSize.sp,
                    color = Color.Gray
                )
            )
        },
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = ImeAction.Go
//        ),
        keyboardActions = KeyboardActions(onDone = {
//            onImeAction()
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = RedOrange,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = LightRed,
        )

    )
}

