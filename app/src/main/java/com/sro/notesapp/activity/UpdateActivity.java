package com.sro.notesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sro.notesapp.R;
import com.sro.notesapp.databinding.ActivityUpdateBinding;
import com.sro.notesapp.model.Note;
import com.sro.notesapp.viewModel.NoteViewModel;

import java.text.DateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    NoteViewModel viewModel;
    String priority;
    int iid;
    String title;
    String subtitle;
    String note;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);
        iid = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        priority = getIntent().getStringExtra("priority");
        note = getIntent().getStringExtra("note");


        binding.notetitle.setText(title);
        binding.notesubtitle.setText(subtitle);
        binding.note.setText(note);

        if (priority.equals("red")) {
            priority = "red";
            binding.red.setImageResource(R.drawable.tick);
            binding.yellow.setImageResource(0);
            binding.green.setImageResource(0);
        }
        if (priority.equals("green")) {
            priority = "green";
            binding.green.setImageResource(R.drawable.tick);
            binding.red.setImageResource(0);
            binding.yellow.setImageResource(0);
        }
        if (priority.equals("yellow")) {
            priority = "yellow";
            binding.yellow.setImageResource(R.drawable.tick);
            binding.red.setImageResource(0);
            binding.green.setImageResource(0);
        }

        binding.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "red";
                binding.red.setImageResource(R.drawable.tick);
                binding.yellow.setImageResource(0);
                binding.green.setImageResource(0);
            }
        });
        binding.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "green";
                binding.green.setImageResource(R.drawable.tick);
                binding.red.setImageResource(0);
                binding.yellow.setImageResource(0);
            }
        });
        binding.yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = "yellow";
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
                    update(title, subtitle, priority, note);
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

    void update(String title, String subtitle, String priority, String note) {

        Note n = new Note(title, subtitle, note, priority);
        Date date = new Date();
        CharSequence charSequence = DateFormat.getTimeInstance().format(date.getTime());
        n.noteDate = charSequence.toString();
        n.id = iid;
        viewModel.update(n);
        finish();
    }


}