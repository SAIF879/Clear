package com.example.clear.screens.home.note.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedOrange
import com.example.clear.utils.commonComponents.CircularButton
import com.example.clear.utils.fonts.FontFamilyClear
@Composable
fun CreateNotesScreen( navController: NavController , noteViewModel: NoteViewModel = hiltViewModel()  ) {

    val inputTitle = remember {
        mutableStateOf("")
    }

    val inputNote = remember {
        mutableStateOf(
            ""
        )
    }

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RedOrange)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                ShowContentCount(content = inputNote.value)
                CircularButton(icon = Icons.Filled.Add) {
                    //onlcik pr save data
                    if (inputTitle.value.isNotEmpty() && inputNote.value.isNotEmpty()) {
                        // add note to view model
                        noteViewModel.addNote(
                            Note(
                                title = inputTitle.value,
                                content = inputNote.value
                            )
                        )
                        inputTitle.value = ""
                        inputNote.value = ""
                        Toast.makeText(context, "note saved ", Toast.LENGTH_SHORT).show()

                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
//            Spacer(modifier = Modifier.weight(0.1f))
                //title
                item {
                    CreateNoteContent(
                        content = inputTitle,
                        placeholder = "Title",
                        fontFamily = FontFamilyClear.fontMedium,
                        fontSize = 25,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
                //content
                item {
                    CreateNoteContent(
                        content = inputNote,
                        placeholder = "Note",
                        fontFamily = FontFamilyClear.fontRegular,
                        fontSize = 18,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    //create bottom bar of colors that change background color and depending on it changes create note color
                    //  ChooseColor()
                }
            }


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateNoteContent(
    content: MutableState<String>,
    placeholder: String,
    fontFamily: FontFamily,
    fontSize: Int,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = content.value,
        onValueChange = { content.value = it },
        textStyle = TextStyle(fontFamily = fontFamily, fontSize = fontSize.sp),
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
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
@Composable
fun ShowContentCount(content: String) {
        Text(
            text =  if (content.isEmpty() )"0 Word" else {"${content.trim().split("\\s+".toRegex()).size} Words" },
            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontMedium),
            maxLines = 1
        )
}
val colors = listOf(
    Color.Red,
    Color.Green,
    Color.Black,
    Color.Blue,
    Color.Gray,
    Color.Magenta
)
@Composable
fun ChooseColor(){
LazyRow(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween){
    items(colors){color->
        ColorCircle(color = color) {}
    }
}
}

@Composable
fun ColorCircle(color: Color = Color.Red , onClick : () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color, shape = CircleShape)
            .aspectRatio(1f)
            .clickable { onClick.invoke() },
    )
}



