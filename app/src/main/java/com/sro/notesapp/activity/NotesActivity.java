package com.sro.notesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.sro.notesapp.R;
import com.sro.notesapp.databinding.ActivityNotesBinding;
import com.sro.notesapp.model.Note;
import com.sro.notesapp.viewModel.NoteViewModel;

import java.text.DateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {
    ActivityNotesBinding binding;
    NoteViewModel noteViewModel;
    String priority = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);
        binding.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "3";
                binding.red.setImageResource(R.drawable.tick);
                binding.yellow.setImageResource(0);
                binding.green.setImageResource(0);
            }
        });
        binding.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "1";
                binding.green.setImageResource(R.drawable.tick);
                binding.red.setImageResource(0);
                binding.yellow.setImageResource(0);
            }
        });
        binding.yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "2";
                binding.yellow.setImageResource(R.drawable.tick);
                binding.red.setImageResource(0);
                binding.green.setImageResource(0);
            }
        });
        binding.addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = binding.notetitle.getText().toString();
                String subtitle = binding.notesubtitle.getText().toString();
                String note = binding.notetitle.getText().toString();
                if (!title.isEmpty() && !subtitle.isEmpty() && !note.isEmpty())
                    createNote(title, subtitle, priority, note);
                else {
                    if (title.isEmpty())
                        binding.notetitle.setError("Cannot be Empty");
                    if (subtitle.isEmpty())
                        binding.notesubtitle.setError("Cannot be Empty");
                    if (note.isEmpty())
                        binding.note.setError("Cannot be Empty");
                }

            }
        });
    }

    private void createNote(String title, String subtitle, String priority, String note) {
        Date date = new Date();
        CharSequence charSequence = DateFormat.getTimeInstance().format(date.getTime());
        Note notes = new Note(title, subtitle, note, priority);
        notes.noteDate = charSequence.toString();
        noteViewModel.insert(notes);
        finish();
    }
}