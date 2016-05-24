package com.exametric_administration.classes.click_listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.exametric_administration.activities.DetailsNote;
import com.exametric_administration.classes.adapters.NoteAdapter;
import com.exametric_administration.classes.classes.Note;

public class OnNoteItemClickListener implements ListView.OnItemClickListener {
    private NoteAdapter noteAdapter;

    public OnNoteItemClickListener(NoteAdapter _noteAdapter) {
        this.noteAdapter = _noteAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = (Note) noteAdapter.getItem(position);
        Intent intent = new Intent(view.getContext(), DetailsNote.class);
        intent.putExtra("idNote", note.GetIdNote());
        view.getContext().startActivity(intent);
    }
}
