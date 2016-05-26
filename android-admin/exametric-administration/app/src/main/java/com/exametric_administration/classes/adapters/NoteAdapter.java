package com.exametric_administration.classes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Note;
import android.content.Context;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Adapteur pour la liste des notes
 */
public class NoteAdapter extends BaseAdapter {
    private ArrayList<Note> notes;
    LayoutInflater inflater;
    Context context;
    TextView noteTextTextView, noteAuthorTextview, noteDateTextView;

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

        noteTextTextView = (TextView) convertView.findViewById(R.id.noteTextTextView);
        noteAuthorTextview = (TextView) convertView.findViewById(R.id.noteAuthorTextView);
        noteDateTextView = (TextView) convertView.findViewById(R.id.noteDateTextView);

        noteTextTextView.setText(note.GetTextNote());
        noteAuthorTextview.setText(convertView.getResources().getString(R.string.notesAdapterAuthorPrefix, note.GetAuthorNote()));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRENCH);
            Date date = sdf.parse(note.GetDateNote());
            noteDateTextView.setText(convertView.getResources().getString(R.string.notesAdapterDatePrefix, date.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public void setData(ArrayList<Note> _notes) {
        this.notes = _notes;
        this.notifyDataSetChanged();
    }

}
