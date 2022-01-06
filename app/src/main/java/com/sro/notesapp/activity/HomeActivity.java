package com.sro.notesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sro.notesapp.R;
import com.sro.notesapp.adapter.NoteAdapter;
import com.sro.notesapp.databinding.ActivityHomeBinding;
import com.sro.notesapp.model.Note;
import com.sro.notesapp.viewModel.NoteViewModel;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    NoteViewModel noteViewModel;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NotesActivity.class));
            }
        });

        noteViewModel.getAllNotes().observe(this, notes -> {
            binding.noteRV.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NoteAdapter(HomeActivity.this, notes);
            binding.noteRV.setAdapter(adapter);
        });

    }

}