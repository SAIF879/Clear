package com.example.clear.screens.home.note.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.utils.fonts.FontFamilyClear
@Preview
@Composable
fun CreateNotesScreen() {

    val inputTitle = remember {
        mutableStateOf("hello world")
    }

    val inputNote = remember {
        mutableStateOf(
            "Faria says he was told by his spirit guides that he must expand his work to reach more people and spiritist medium Chico Xavier told him he should go to the small Goiás town of Abadiânia to fulfill his healing mission. Around 1978, when João first " +
                    "performed healings there, he just sat outdoors in a chair near the main road where people began to arrive seeking cures for their illnesses and conditions. Gradually the numbers increased to thousands per day and he developed his centre, Casa de Dom I" +
                    "nácio de Loyola.[13] The Casa de Dom Inácio de Loyola has since been visited by millions of people seeking healing. He also owns a nearby cattle ranch, which covers about 1,000 acres and is valued at over 2 million reais.[14][15]\n Much of his income comes" +
                    " from selling passionflower preparations, the single herb prescribed by Faria to cure a variety of ailments. The company which bears João Teixeira Faria's initials, JTF Ltda., markets the drug and is registered in the name of his wife, Ana Keyla Teixeira, and his " +
                    "driver and employee Abadio da Cruz"
        )
    }

    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)) {
//            Spacer(modifier = Modifier.weight(0.1f))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()) {
                ShowContentCount(content = inputNote.value)
                CircularButton(icon = Icons.Filled.Add)
//                Spacer(modifier = Modifier.width(20.dp))

            }
            //title
            CreateNoteContent(title = inputTitle , placeholder = "Title" , fontFamily = FontFamilyClear.fontMedium , fontSize = 25 , modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight())
            //content
            CreateNoteContent(title = inputNote, placeholder = "Note", fontFamily =FontFamilyClear.fontRegular , fontSize = 18 , modifier = Modifier
                .fillMaxWidth()
                .weight(1f) )

            //create bottom bar of colors that change background color and depending on it changes create note color
        }

        }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateNoteContent(title: MutableState<String>, placeholder: String, fontFamily: FontFamily, fontSize : Int , modifier: Modifier ) {
    val keyboardController= LocalSoftwareKeyboardController.current
    TextField(
        value = title.value,
        onValueChange = {title.value = it},
        textStyle = TextStyle(fontFamily = fontFamily, fontSize = fontSize.sp),
        modifier = modifier
          ,
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
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onDone = {
//            onImeAction()
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}
@Composable
fun ShowContentCount(content: String) {
        Text(
            text = "${content.trim().split("\\s+".toRegex()).size} Words",
            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamilyClear.fontMedium),
            maxLines = 1
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateNoteInputField(text: MutableState<String>, placeholder: String , height : Int) {
    Box(modifier = Modifier
        .height(height.dp)
        .fillMaxWidth()) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .height(height.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ) {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(fontSize = 20.sp, fontFamily = FontFamilyClear.fontRegular),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontFamily = FontFamilyClear.fontMedium,
                            color = Color.Gray,
                            fontSize = 22.sp
                        )
                    )
                }
            )
        }
    }
}