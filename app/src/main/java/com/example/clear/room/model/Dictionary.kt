package com.example.clear.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dictionary_table")
data class Dictionary (
    @PrimaryKey
    @ColumnInfo(name = "word_name")
    val wordName : String,
    @ColumnInfo(name = "is_saved")
    val isSaved : Boolean = false,
    @ColumnInfo(name = "is_searched")
    val isSearched : Boolean = false

        )
