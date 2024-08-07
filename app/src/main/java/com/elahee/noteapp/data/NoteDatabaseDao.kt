package com.elahee.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.elahee.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl")
    fun getNote(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id=:id")
    fun getNoteById(id:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note:Note)

    @Query("DELETE from notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note:Note)
}
