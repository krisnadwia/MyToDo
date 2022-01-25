package com.krisnadwia.mytodo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krisnadwia.mytodo.entity.Note

@Dao
interface NotesDao {
    // inserting mytodo
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(note: Note)

    // deleting mytodo
    @Delete
    suspend fun deleteNotes(note: Note)

    // getting all the notes
    @Query("select * from my_notes order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}