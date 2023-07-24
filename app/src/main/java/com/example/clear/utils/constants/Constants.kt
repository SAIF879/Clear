package com.example.clear.utils.constants

import android.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.clear.room.model.Note
import com.example.clear.ui.theme.RedOrange
import java.util.UUID

object Constants {
    const val BASE_URL = "https://api.dictionaryapi.dev"
    val noteDetailPlaceHolder = Note(content = "Cannot find note details", title = "Cannot find note details" , color = RedOrange.toArgb())
}