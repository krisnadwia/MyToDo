package com.krisnadwia.mytodo.repository

import androidx.lifecycle.LiveData
import com.krisnadwia.mytodo.dao.NotesDao
import com.krisnadwia.mytodo.entity.Note

class NoteRepository(private val notesDao: NotesDao) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insertNotes(note: Note) {
        notesDao.insertNotes(note)
    }

    suspend fun deleteNotes(note: Note) {
        notesDao.deleteNotes(note)
    }
}