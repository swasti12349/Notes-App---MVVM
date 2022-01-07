package com.sro.notesapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sro.notesapp.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteTable")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM NoteTable ORDER BY priority ASC")
    LiveData<List<Note>> lowtohigh();

    @Query("SELECT * FROM NoteTable ORDER BY priority DESC")
    LiveData<List<Note>> hightolow();

    @Insert
    void insert(Note...notes);

    @Update
    void update(Note...notes);

    @Query("DELETE FROM NoteTable WHERE ID = :id")
    void delete(int id);

}
