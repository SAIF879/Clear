package com.example.clear.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "entrance_table")
data class UserEntrance (
    @PrimaryKey val id : UUID = UUID.randomUUID(),
    val hasSeenIntroScreens  : Boolean = false
        )