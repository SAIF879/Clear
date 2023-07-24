package com.example.clear.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todo_table")
data class Todo (

   @PrimaryKey  val id : UUID = UUID.randomUUID(),

   @ColumnInfo(name = "todo_content")
    val content : String,

   @ColumnInfo(name = "is_completed")
   val isCompleted : Boolean = false,

   @ColumnInfo(name = "time_stamp")
   val timeStamp : Long = System.currentTimeMillis()

)

