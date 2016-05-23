package com.exametric_administration.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.exametric_administration.R;
import com.exametric_administration.classes.adapters.NoteAdapter;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.controllers.NoteController;
import com.exametric_administration.tools.RealmConfig;

import android.os.Handler;
import java.util.ArrayList;

public class NoteListView extends AppCompatActivity {
    private static ListView notesListView;
    private static int idArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);
        idArea = getIntent().getExtras().getInt("idArea");
        NoteController.downloadNotesById(getBaseContext(), idArea);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("REGRTHSVTHYCXHVBYVTJNCGNVFBHVYFHDYFGJBDHTYVGDNVTYBFHDVNTYVGHXDBRYCFXJG");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpAdapter(RealmNote.getNotesByAreaId(RealmConfig.realmInstance));
                System.out.println("SON PERE BLBLBLBLBLBLBL");
            }
        }, 2000);
    }

    public void setUpAdapter(ArrayList<Note> _notes) {
        notesListView = (ListView) findViewById(R.id.noteListView);
        NoteAdapter noteAdapter = new NoteAdapter(getBaseContext(), _notes);
        notesListView.setAdapter(noteAdapter);
        notesListView.setOnItemClickListener(new OnNoteItemClickListener(noteAdapter));
    }

}

class OnNoteItemClickListener implements ListView.OnItemClickListener {
    private NoteAdapter noteAdapter;

    public OnNoteItemClickListener(NoteAdapter _noteAdapter) {
        this.noteAdapter = _noteAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = (Note) noteAdapter.getItem(position);
        Toast.makeText(view.getContext(), note.GetAuthorNote(), Toast.LENGTH_SHORT).show();
        //TODO Display the entire note
    }
}
