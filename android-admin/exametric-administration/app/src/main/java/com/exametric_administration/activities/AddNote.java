package com.exametric_administration.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.controllers.NoteController;
import com.exametric_administration.tools.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class AddNote extends AppCompatActivity implements View.OnClickListener {
    private EditText authorEditText, textEditText;
    private Button sendButton;
    private String color;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        color = getIntent().getExtras().getString("color");
        Toolbar toolbar = (Toolbar) findViewById(R.id.addNoteToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.add_note_appbar_title));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80" + color)));
        actionBar.setDisplayShowTitleEnabled(true);

        authorEditText = (EditText) findViewById(R.id.addNoteAuthorEditText);
        textEditText = (EditText) findViewById(R.id.addNoteTextEditText);
        sendButton = (Button) findViewById(R.id.addNoteSendButton);

        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNoteSendButton:
                Note note = new Note(0, authorEditText.getText().toString(), textEditText.getText().toString(), new Date().toString(), GlobalVariables.ACTUAL_AREA);
                NoteController.uploadNote(this, note, String.valueOf(GlobalVariables.ACTUAL_AREA));
                Intent intent = new Intent(this, NoteListView.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
