package com.sro.notesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sro.notesapp.dao.NoteDao;
import com.sro.notesapp.database.NoteDatabase;
import com.sro.notesapp.model.Note;

import java.util.List;

public class NoteRepository {

    public NoteDao noteDao;
    LiveData<List<Note>> notesList;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.mainDao();
        notesList = noteDao.getAllNotes();
    }

    public void insertNote(Note note){
        noteDao.insert(note);
    }

    public void deleteNote(int id){
        noteDao.delete(id);
    }

    public void updateNote(Note note){
        noteDao.update(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return notesList;
    }
}
