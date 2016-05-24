package com.exametric_administration.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsNote extends AppCompatActivity {
    private String color;
    private Note note;
    private TextView textNoteTextView, dateNoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_note);
        note = RealmNote.getNoteById(RealmConfig.realmInstance, getIntent().getExtras().getInt("idNote"));
        color = getIntent().getExtras().getString("color");
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailsNoteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.notesAdapterAuthorPrefix, note.GetAuthorNote()));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80" + color)));
        actionBar.setDisplayShowTitleEnabled(true);
        textNoteTextView = (TextView) findViewById(R.id.textDetailsNoteTextView);
        dateNoteTextView = (TextView) findViewById(R.id.dateDetailsNoteTextView);
        textNoteTextView.setText(note.GetTextNote());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRENCH);
            Date date = sdf.parse(note.GetDateNote());
            dateNoteTextView.setText(getResources().getString(R.string.notesAdapterDatePrefix, date.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
