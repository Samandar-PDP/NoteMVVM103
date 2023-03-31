package com.sdk.mvvmroomdb103.repository

import com.sdk.mvvmroomdb103.database.Note
import com.sdk.mvvmroomdb103.database.NoteDao

class NoteRepository(
    private val dao: NoteDao
) {
    fun getAllNotes() = dao.getAllNotes()
    suspend fun saveNote(note: Note) = dao.saveNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
    suspend fun updateNote(note: Note) = dao.updateNote(note)
    suspend fun deleteAllNotes() = dao.clearNote()
}