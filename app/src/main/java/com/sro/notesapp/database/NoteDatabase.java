package com.sro.notesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sro.notesapp.dao.NoteDao;
import com.sro.notesapp.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao mainDao();

    private static NoteDatabase database;

    final private static String DATABASE_NAME = "database";

    public synchronized static NoteDatabase getInstance(Context context) {
        if (database == null) {
            //When database is null
            database = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }


}