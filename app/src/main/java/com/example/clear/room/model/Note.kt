package com.example.clear.room.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clear.ui.theme.BabyBlue
import com.example.clear.ui.theme.LightGreen
import com.example.clear.ui.theme.RedOrange
import com.example.clear.ui.theme.RedPink
import com.example.clear.ui.theme.Violet
import java.util.UUID


@Entity(tableName = "note_tbl")
data class Note(

    @PrimaryKey val id:  UUID =UUID.randomUUID(),

    @ColumnInfo(name="note_title")
    val title : String ,

    @ColumnInfo(name = "note_content")
    val content : String ,

    @ColumnInfo(name = "note_color")
    val color : Int  ,



){
    companion object{
       val noteColors = listOf(RedOrange , LightGreen , Violet , BabyBlue , RedPink)
    }
}
