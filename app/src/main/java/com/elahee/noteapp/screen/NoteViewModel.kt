package com.elahee.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.elahee.noteapp.data.NotesDataSource
import com.elahee.noteapp.model.Note

class NoteViewModel: ViewModel() {

    val noteList= mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }
    fun addNote(note: Note){
        noteList.add(note)
    }
    fun removeNote(note: Note){
        noteList.remove(note)
    }
    fun getAllNotes(): List<Note>{
        return noteList
    }
}