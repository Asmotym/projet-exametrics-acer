package com.exametric_administration.classes;


import java.sql.Date;

public class Note {

    private int _idNote;
    private String _authorNote;
    private String _textNote;
    private Date _dateNote;

    public int GetIdNote() {
        return _idNote;
    }

    public void SetIdNote(int idNote) {
        this._idNote = idNote;
    }

    public String GetAuthorNote() {
        return _authorNote;
    }

    public void SetAuthorNote(String authorNote) {
        this._authorNote = authorNote;
    }

    public String GetTextNote() {
        return _textNote;
    }

    public void SetTextNote(String textNote) {
        this._textNote = textNote;
    }

    public Date GetDateNote() {
        return _dateNote;
    }

    public void SetDateNote(Date dateNote) {
        this._dateNote = dateNote;
    }

    public

}
