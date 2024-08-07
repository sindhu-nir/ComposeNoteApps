package com.elahee.noteapp.repository

import com.elahee.noteapp.data.NoteDatabaseDao
import com.elahee.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = withContext(Dispatchers.IO) { noteDatabaseDao.insert(note) }
    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {noteDatabaseDao.update(note)}
    suspend fun deleteNote(note: Note) = withContext(Dispatchers.IO) {noteDatabaseDao.deleteNote(note)}
    suspend fun deleteAll() = noteDatabaseDao.deleteAll()
    suspend fun getAllNotes(): Flow<List<Note>> =
        noteDatabaseDao.getNote().flowOn(Dispatchers.IO).conflate()
}