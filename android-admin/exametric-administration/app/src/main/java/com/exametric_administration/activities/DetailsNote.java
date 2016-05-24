package com.exametric_administration.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.tools.RealmConfig;


public class DetailsNote extends AppCompatActivity {
    private static Note note;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.details_note);
        note = RealmNote.getNoteById(RealmConfig.realmInstance, getIntent().getExtras().getInt("idArea"));
        String color = getIntent().getExtras().getString("color");
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailsNoteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(note.GetAuthorNote());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80" + color)));
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
