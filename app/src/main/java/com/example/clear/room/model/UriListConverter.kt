package com.example.clear.room.model

import android.net.Uri
import androidx.room.TypeConverter

class UriListConverter {
    @TypeConverter
    fun fromUriList(uriList: List<Uri?>): String {
        return uriList.joinToString(",") { it?.toString() ?: "" }
    }

    @TypeConverter
    fun toUriList(data: String): List<Uri?> {
        return data.split(",").map { if (it.isNotEmpty()) Uri.parse(it) else null }
    }
}