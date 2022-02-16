package com.example.mynotapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotapplication.model.Notes
import com.example.mynotapplication.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val notesRepository: NotesRepository) :
    ViewModel() {
    fun insertNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNote(notes)
        }
    }
}