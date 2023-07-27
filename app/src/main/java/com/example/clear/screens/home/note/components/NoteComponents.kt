package com.example.clear.screens.home.note.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.clear.R
import com.example.clear.room.model.Note
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.commonComponents.util.bounceClick
import com.example.clear.utils.fonts.FontFamilyClear
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun NotesCard(note: Note, viewModel: NoteViewModel, onclick: () -> Unit) {
    val delete = SwipeAction(
        onSwipe = {
            viewModel.removeNote(note)
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.DeleteForever,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier.padding(0.dp)
            )
        },
        background = RedPink

    )
    SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 150.dp) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(note.color))
                .height(200.dp)
                .padding(10.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    onclick.invoke()
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(note.color))
            ) {
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .background(Color(note.color))
                ) {
                    Text(
                        text = note.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamilyClear.fontMedium,
                            color = TextWhite
                        )
                    )
                    EllipsizeText(text = note.content)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WordsCount(content = note.content)
                    SwipeToDeleteSign()
                }
            }
        }
    }
}

@Composable
fun WordsCount(content: String) {
    val text = content.trim().split("\\s".toRegex())
    Text(
        text = if (content.isEmpty()) "0 Word" else "${text.size}" + if (text.size == 1) " Word" else " Words",
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamilyClear.fontMedium,
            color = TextWhite
        ),
        maxLines = 1
    )
}


@Composable
fun SwipeToDeleteSign(){
Row {
    Icon(imageVector =Icons.Filled.Delete , contentDescription ="delete_icon" )
    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="back_arrow" )
}
}


@Composable
fun EllipsizeText(text: String, maxLength: Int = 100) {
    val ellipsizedText = remember(text) {
        if (text.length > maxLength) {
            text.substring(0, maxLength - 3) + "..."
        } else {
            text
        }
    }

    Text(
        text = ellipsizedText,
        style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontRegular)
    )
}




@Composable
fun ColorCircle(color : Color , onClick : () -> Unit){
    Box(modifier = Modifier
        .size(50.dp)
        .border(2.dp, Color.Black, CircleShape)
        .background(color = color, shape = CircleShape)
        .aspectRatio(1f)
        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
            onClick.invoke()
        }
    )
}

@Composable
fun ChosenColor(modifier: Modifier, onSelectColor: (Color) -> Unit) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(Note.noteColors){
            ColorCircle(color = it) {
                onSelectColor.invoke(it)
            }
        }
    }
}
@Composable
fun CreateNoteContent(
    content: MutableState<String>,
    placeholder: String,
    fontFamily: FontFamily,
    fontSize: Int,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onAction : KeyboardActions = KeyboardActions.Default

) {

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
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = LightRed,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType , imeAction = imeAction),
        keyboardActions = onAction

    )
}

@Composable
fun ImageCard(modifier: Modifier = Modifier, selectedImageUri: Uri?) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp) , shape = RectangleShape
    ) {
        if (selectedImageUri != null)
        AsyncImage(
            model = selectedImageUri,
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        else
            Icon(
                imageVector = Icons.Filled.Image,
                contentDescription = "Image_icon",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            )
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
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}



@Composable
fun LocalGreeting() {
    Text(
        text = localGreeting() ,
        style = TextStyle(
            color = TextWhite,
            fontSize = 20.sp,
            fontFamily = FontFamilyClear.fontMedium
        )
    )
}

fun localGreeting() : String{
    val currentTime = Date()
    val timeFormat = SimpleDateFormat("HH", Locale.getDefault())

    return when(timeFormat.format(currentTime).toInt()){
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Night"
        else -> "Good Night"
    }
}








