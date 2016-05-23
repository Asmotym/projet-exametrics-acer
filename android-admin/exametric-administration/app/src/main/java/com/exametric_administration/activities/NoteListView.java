package com.exametric_administration.activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    private static String colorArea, nameArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);
        idArea = getIntent().getExtras().getInt("idArea");
        colorArea = getIntent().getExtras().getString("colorArea");
        nameArea = getIntent().getExtras().getString("nameArea");
        Toolbar toolbar = (Toolbar) findViewById(R.id.noteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(nameArea);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80" + colorArea.substring(4))));

        actionBar.setDisplayShowTitleEnabled(true);
        NoteController.downloadNotesById(getBaseContext(), idArea);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpNotesAdapter(RealmNote.getAllNotes(RealmConfig.realmInstance));
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void setUpNotesAdapter(ArrayList<Note> _notes) {
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
        //TODO Display the entire note
    }
}
