package com.exametric_administration.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.exametric_administration.R;

public class DetailsNote extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.details_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailsNoteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

    }
}
