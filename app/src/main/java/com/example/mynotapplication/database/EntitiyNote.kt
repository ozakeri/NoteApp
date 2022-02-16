package com.example.mynotapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotapplication.model.Notes

@Entity(tableName = "noteTable")
data class EntitiyNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val notes: Notes
)
