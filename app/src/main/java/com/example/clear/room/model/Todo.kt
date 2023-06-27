package com.example.clear.room.model

import java.util.UUID

data class Todo (
    val id : UUID = UUID.randomUUID(),
    val content : String
        )