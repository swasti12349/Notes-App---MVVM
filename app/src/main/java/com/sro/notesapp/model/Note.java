package com.sro.notesapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoteTable")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "noteTitle")
    public String noteTitle;

    @ColumnInfo(name = "noteSubTitle")
    public String noteSubTitle;

    @ColumnInfo(name = "noteDate")
    public String noteDate;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "priority")
    public String priority;

    public Note(String noteTitle, String noteSubTitle, String notes, String priority) {
        this.noteTitle = noteTitle;
        this.noteSubTitle = noteSubTitle;
        this.notes = notes;
        this.priority = priority;
    }
}
