package com.example.mynotapplication.repository

import androidx.room.TypeConverters
import com.besenior.kotlinadvancedcourse.room.NoteTypeConverter
import com.example.mynotapplication.database.AppRoomDatabase
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.model.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(appRoomDatabase: AppRoomDatabase) {

    private val dao = appRoomDatabase.noteDao();
    fun insertNote(note : Notes) {
        val entitiyNote = EntitiyNote(0,note)
        dao.insert(entitiyNote)
    }

    fun getAllNotes() : Flow<List<EntitiyNote>>{
        return dao.getAllNotes()
    }
}