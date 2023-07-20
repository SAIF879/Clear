package com.example.clear.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "dictionary_table")
data class Dictionary (
    @PrimaryKey
    val id : UUID = UUID.randomUUID(),
    @ColumnInfo(name = "word_name")
    val WordName : String,
    @ColumnInfo(name = "is_saved")
    val isSaved : Boolean = false,
    @ColumnInfo(name = "is_searched")
    val isSearched : Boolean = false


        )
