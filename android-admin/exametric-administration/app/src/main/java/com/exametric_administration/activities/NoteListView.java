package com.exametric_administration.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.exametric_administration.R;
import com.exametric_administration.classes.adapters.NoteAdapter;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.classes.click_listeners.OnNoteItemClickListener;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.controllers.NoteController;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;
import android.os.Handler;
import java.util.ArrayList;

public class NoteListView extends AppCompatActivity {
    private static ListView notesListView;
    private static Area area;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);

        // Si ACTUAL_AREA n'est pas égale à 0 c'est que l'utilisateur à fait un retour arrière donc ont récupère la zone actuel
        if (GlobalVariables.ACTUAL_AREA == 0) {
            area = RealmArea.getAreaById(RealmConfig.realmInstance, getIntent().getExtras().getInt("idArea"));
        } else {
            area = RealmArea.getAreaById(RealmConfig.realmInstance, GlobalVariables.ACTUAL_AREA);
        }

        // On paramètre la toolbar
        setUpActionBar();

        // Ont télécharge les notes cela peut prendre un peu de temps
        NoteController.downloadNotesById(getBaseContext(), area.GetIdArea());
    }

    @Override
    public void onResume() {
        super.onResume();
        // On affiche les notes déjà dans Realm
        setUpNotesAdapter(RealmNote.getAllNotes(RealmConfig.realmInstance));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setUpActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.noteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(area.GetNameArea());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80" + area.GetColorArea().substring(4))));
        actionBar.setDisplayShowTitleEnabled(true);
    }

    /**
     * Mise en place de la ListView et son Adapter
     * @param _notes
     */
    public void setUpNotesAdapter(ArrayList<Note> _notes) {
        notesListView = (ListView) findViewById(R.id.noteListView);
        NoteAdapter noteAdapter = new NoteAdapter(getBaseContext(), _notes);
        notesListView.setAdapter(noteAdapter);
        notesListView.setOnItemClickListener(new OnNoteItemClickListener(noteAdapter, area.GetColorArea().substring(4)));
    }

    /**
     * Pour avertir d'un changement dans les données.
     * @param _notes
     */
    public static void notifyDataChange(ArrayList<Note> _notes) {
        NoteAdapter noteAdapter = (NoteAdapter) notesListView.getAdapter();
        noteAdapter.setData(_notes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuReload:
                NoteController.downloadNotesById(this, area.GetIdArea());
                return true;
            case R.id.menuAdd:
                GlobalVariables.ACTUAL_AREA = area.GetIdArea();
                Intent intent = new Intent(this, AddNote.class);
                intent.putExtra("color", area.GetColorArea().substring(4));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
