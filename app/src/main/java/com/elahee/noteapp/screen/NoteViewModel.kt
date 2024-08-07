package com.elahee.noteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elahee.noteapp.data.NotesDataSource
import com.elahee.noteapp.model.Note
import com.elahee.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private var _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList=_noteList.asStateFlow()
    val TAG: String = "NoteViewModel"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged().collect{ listOfNotes->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d(TAG, "EmptyList ")
                  //  _noteList.value= emptyList()
                }
                _noteList.value=listOfNotes

            }
        }
    }
    fun addNote(note: Note)= viewModelScope.launch {
        noteRepository.addNote(note)
    }
    fun updateNote(note: Note)= viewModelScope.launch {
        noteRepository.updateNote(note)
    }
    fun removeNote(note: Note)= viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

}