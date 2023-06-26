package com.example.clear.room.model

class NoteData {
    fun loadNotes() : List<Note>{
        return listOf(
            Note(title = "A good day", content = "A good day with dooday"),
            Note(title = "A ersdf day", content = "A good day with dooday"),
            Note(title = "A asf day", content = "A good day with dooday"),
        )
    }
}