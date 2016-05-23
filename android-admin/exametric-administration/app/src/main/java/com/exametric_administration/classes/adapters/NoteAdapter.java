package com.exametric_administration.classes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Note;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    private ArrayList<Note> notes;
    LayoutInflater inflater;
    Context context;
    TextView noteTextTextView;

    public NoteAdapter(Context _context, ArrayList<Note> _notes) {
        this.notes = _notes;
        this.context = _context;
        inflater = LayoutInflater.from(_context);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_note_item, parent, false);
        }
        Note note = notes.get(position);

        noteTextTextView = (TextView) convertView.findViewById(R.id.noteTextTextField);
        noteTextTextView.setText(note.GetTextNote().substring(0, 30));

        return convertView;
    }
}
