package com.besenior.kotlinadvancedcourse.room

import androidx.room.TypeConverter
import com.example.mynotapplication.model.Notes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NoteTypeConverter {

    @TypeConverter
    fun tojson(notesModel: Notes): String? {
        if (notesModel == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Notes?>() {}.getType()
        return gson.toJson(notesModel, type)
    }

    @TypeConverter
    fun toDataClass(note: String?): Notes? {
        if (note == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Notes?>() {}.getType()
        return gson.fromJson(note, type)
    }
}