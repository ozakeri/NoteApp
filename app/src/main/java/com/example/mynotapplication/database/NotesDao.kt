package com.example.mynotapplication.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM noteTable")
    fun getAllNotes(): Flow<List<EntitiyNote>>

    @Insert
    fun insert(note: EntitiyNote)

    @Update
    fun update(note: EntitiyNote)

    @Delete
    fun delete(note: EntitiyNote)
}