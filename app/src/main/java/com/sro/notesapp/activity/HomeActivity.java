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
    int b = 0;


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


            getAllNotes();

        binding.noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllNotes();
            }
        });
        binding.hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hightolow();
            }
        });
        binding.lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowtohigh();
            }
        });


    }

    void hightolow() {
        noteViewModel.hightolow.observe(this, notes -> {
            binding.noteRV.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NoteAdapter(HomeActivity.this, notes);
            binding.noteRV.setAdapter(adapter);
            b = 1;
        });
    }

    void lowtohigh() {
        noteViewModel.lowtohigh.observe(this, notes -> {
            binding.noteRV.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NoteAdapter(HomeActivity.this, notes);
            binding.noteRV.setAdapter(adapter);
            b = 2;
        });
    }

    void getAllNotes() {
        noteViewModel.getAllNotes.observe(this, notes -> {
            binding.noteRV.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NoteAdapter(HomeActivity.this, notes);
            binding.noteRV.setAdapter(adapter);
        });
    }
}