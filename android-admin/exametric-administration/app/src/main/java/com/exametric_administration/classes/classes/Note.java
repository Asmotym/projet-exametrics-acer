package com.exametric_administration.classes.classes;

import java.util.Date;
import io.realm.RealmObject;

public class Note extends RealmObject {

    private int idNote;
    private String authorNote;
    private String textNote;
    private String dateNote;
    private int idArea;

    public int GetIdNote() {
        return idNote;
    }

    public void SetIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String GetAuthorNote() {
        return authorNote;
    }

    public void SetAuthorNote(String authorNote) {
        this.authorNote = authorNote;
    }

    public String GetTextNote() {
        return textNote;
    }

    public void SetTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String GetDateNote() {
        return dateNote;
    }

    public void SetDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public int GetIdArea() {
        return this.idArea;
    }

    public void SetIdArea(int idArea) {
        this.idArea = idArea;
    }

    public Note() {}

    public Note(int idNote, String authorNote, String textNote, String dateNote, int idArea) {
        SetIdNote(idNote);
        SetAuthorNote(authorNote);
        SetTextNote(textNote);
        SetDateNote(dateNote);
        SetIdArea(idArea);
    }

}
