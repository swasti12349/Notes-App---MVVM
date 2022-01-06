package com.sro.notesapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sro.notesapp.model.Note;
import com.sro.notesapp.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository repository;
    LiveData<List<Note>> getAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        getAllNotes = repository.noteDao.getAllNotes();
    }

    public void insert(Note note) {
        repository.noteDao.insert(note);
    }

    public void delete(int id) {
        repository.noteDao.delete(id);
    }

    public void update(Note note) {
        repository.noteDao.update(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return getAllNotes;
    }
}
