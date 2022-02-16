package com.example.mynotapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.besenior.kotlinadvancedcourse.room.NoteTypeConverter

@TypeConverters(NoteTypeConverter::class)
@Database(entities = [EntitiyNote::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun noteDao() : NotesDao
}