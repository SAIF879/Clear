package com.example.clear.screens.home.note.ui


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.components.ChosenColor
import com.example.clear.screens.home.note.components.CreateNoteContent
import com.example.clear.screens.home.note.components.WordsCount
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.Pink80
import com.example.clear.utils.commonComponents.ui.CircularButton
import com.example.clear.utils.commonComponents.ui.StatusBarColor
import com.example.clear.utils.commonComponents.util.bounceClick
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
                item {
                   AddImageButton(icon = Icons.Filled.Image){}
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

@Composable
fun ImageBox(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.Red), contentAlignment = Alignment.Center
    ){
        Text(text ="asfddasfads")
    }
}

@Composable
fun AddImagesBox(onClick : () -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Pink80)
        .fillMaxHeight()
        .padding(10.dp)
        .clickable { onClick.invoke() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(imageVector = Icons.Filled.Image, contentDescription = "image_icon" , tint= Color.White )

            Text(
                text = "",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}



@Composable
fun AddImageButton(modifier: Modifier = Modifier, icon : ImageVector, size : Int = 50, onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .bounceClick()
            .fillMaxHeight()
            .width(200.dp)
            .background(LightRed, shape = CircleShape)
            .aspectRatio(1f)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = " icon",
            tint = Color.White,
            modifier = Modifier.fillMaxSize().padding(20.dp)
        )
    }
}




