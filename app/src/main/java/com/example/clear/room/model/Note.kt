package com.example.clear.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "note_tbl")
data class Note(

    @PrimaryKey val id:  UUID =UUID.randomUUID(),

    @ColumnInfo(name="note_title")
    val title : String ,
    @ColumnInfo(name = "note_content")
    val content : String ,
    @ColumnInfo(name = "note_time_stamp")
    val timeStamp : Long, // tells when we created that note  --> may change  to  Date if needed
    @ColumnInfo(name = "note_color")
    val color : Int ,

    @ColumnInfo(name = "note_favourite")
    val isFavourite : Boolean

){
    companion object{
  //      val noteColors = listOf(RedOrange , LightGreen , Violet , BabyBlue , RedPink)
    }
}
