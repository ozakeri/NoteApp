package com.example.mynotapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.model.Notes
import com.example.mynotapplication.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val notesRepository: NotesRepository) :
    ViewModel() {

    private val mutableLiveData : MutableLiveData<List<EntitiyNote>> = MutableLiveData()
    val liveData : LiveData<List<EntitiyNote>> = mutableLiveData;

    init {
        getAllNotes()
    }

    fun insertNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNote(notes)
        }
    }

    fun getAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getAllNotes().collect {
                mutableLiveData.postValue(it)
            }
        }
    }
}