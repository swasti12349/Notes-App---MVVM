package com.sro.notesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.sro.notesapp.R;
import com.sro.notesapp.adapter.NoteAdapter;
import com.sro.notesapp.databinding.ActivityHomeBinding;
import com.sro.notesapp.model.Note;
import com.sro.notesapp.viewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    NoteViewModel noteViewModel;
    NoteAdapter adapter;
    int b = 0;
    List<Note> filterNoteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);
        filterNoteList = new ArrayList<>();
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
            filterNoteList = notes;
            binding.noteRV.setAdapter(adapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFiilter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFiilter(String s) {
        Log.d("mklmkl", s);
        ArrayList<Note> arrayList = new ArrayList<>();

        for(Note n: filterNoteList){
            if (n.noteTitle.contains(s) || n.noteSubTitle.contains(s)){
                arrayList.add(n);
            }
        }

        this.adapter.searchNote(arrayList);
    }
}

