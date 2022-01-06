package com.sro.notesapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.sro.notesapp.R;
import com.sro.notesapp.activity.UpdateActivity;
import com.sro.notesapp.databinding.NoteitemBinding;
import com.sro.notesapp.model.Note;
import com.sro.notesapp.viewModel.NoteViewModel;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.myViewHolder> {
    Context context;
    List<Note> noteList;
    NoteViewModel noteViewModel;

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.noteitem, parent, false));
    }

    public Note getNoteAt(int position) {
        return noteList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.myViewHolder holder, int position) {
        Note n = noteList.get(position);
        holder.binding.title.setText(n.noteTitle);
        holder.binding.subtitle.setText(n.noteSubTitle);
        holder.binding.date.setText(n.noteDate);
        noteViewModel = ViewModelProviders.of((FragmentActivity) context)
                .get(NoteViewModel.class);
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteViewModel.delete(n.id);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", n.id);
                intent.putExtra("title", n.noteTitle);
                intent.putExtra("subtitle", n.noteSubTitle);
                intent.putExtra("priority", n.priority);
                intent.putExtra("note", n.notes);

                context.startActivity(intent);
            }
        });
        if (n.priority.equals("red")) {
            holder.binding.priority.setImageResource(R.drawable.red);
        }
        if (n.priority.equals("yellow")) {
            holder.binding.priority.setImageResource(R.drawable.yellow);
        }
        if (n.priority.equals("green")) {
            holder.binding.priority.setImageResource(R.drawable.green);
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        NoteitemBinding binding;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NoteitemBinding.bind(itemView);
        }
    }
}
