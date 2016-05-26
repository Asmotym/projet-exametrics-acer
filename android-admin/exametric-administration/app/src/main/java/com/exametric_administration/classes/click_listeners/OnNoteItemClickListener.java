package com.exametric_administration.classes.click_listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.exametric_administration.activities.DetailsNote;
import com.exametric_administration.classes.adapters.NoteAdapter;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.tools.GlobalVariables;

public class OnNoteItemClickListener implements ListView.OnItemClickListener {
    private NoteAdapter noteAdapter;
    private String color;

    public OnNoteItemClickListener(NoteAdapter _noteAdapter, String _color) {
        this.noteAdapter = _noteAdapter;
        this.color = _color;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Ont récupère la note et on envoie son ID et la couleur de la zone a l'activité DetailsNote
        Note note = (Note) noteAdapter.getItem(position);
        GlobalVariables.ACTUAL_AREA = note.GetIdArea();
        Intent intent = new Intent(view.getContext(), DetailsNote.class);
        intent.putExtra("idNote", note.GetIdNote());
        intent.putExtra("color", color);
        view.getContext().startActivity(intent);
    }
}
