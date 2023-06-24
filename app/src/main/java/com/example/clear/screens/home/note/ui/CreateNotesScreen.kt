package com.example.clear.screens.home.note.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun CreateNotesScreen(navController: NavController) {

    val inputNote = remember {
        mutableStateOf("")
    }

    val inputTitle = remember{
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column( horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxSize()) {
            GenerateNoteInputField(text = inputTitle, placeholder ="Enter Note Title" , height = 80 )
            GenerateNoteInputField(text = inputNote , "Enter your note here" , 500)
            ButtonRow {
                GenerateSmallButtons("cancel",color = Color.Red){}
                GenerateSmallButtons("Save",color = Color.Green){}
            }
        }
        }

}

@Composable
private fun ButtonRow(content: @Composable () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        content()

    }
}


@Composable
fun GenerateSmallButtons(text: String , enabled: Boolean = true,color : Color , onClick: () -> Unit ={}) {
    Button(onClick = { onClick.invoke() },   modifier = Modifier
        .wrapContentHeight()
        .width(200.dp)
        .padding(20.dp, 20.dp),
        enabled = enabled, shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),) {
        Text(
            text = text,
            style = TextStyle(fontSize = 12.sp, fontFamily = FontFamilyClear.fontMedium),
            textAlign = TextAlign.Center
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateNoteInputField(text: MutableState<String>, placeholder: String , height : Int) {
    Box(modifier = Modifier.height(height.dp).fillMaxWidth()) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .height(height.dp).fillMaxWidth()
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