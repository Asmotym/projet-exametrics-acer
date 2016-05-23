package com.gaming.beehaku.myapplication.Classes;

/**
 * Created by Beehaku on 19/05/2016.
 */
public class Note {

    private int _id;
    private String _author;
    private String _text;
    private Area theArea;

    public Note(int _id, String _author, String _text, Area theArea) {
        this._id = _id;
        this._author = _author;
        this._text = _text;
        this.theArea = theArea;
    }

    public int GetId() {
        return _id;
    }

    public void SetId(int _id) {
        this._id = _id;
    }

    public String GetAuthor() {
        return _author;
    }

    public void SetAuthor(String _author) {
        this._author = _author;
    }

    public String GetText() {
        return _text;
    }

    public void SetText(String _text) {
        this._text = _text;
    }

    public Area GetTheArea() {
        return theArea;
    }

    public void SetTheArea(Area theArea) {
        this.theArea = theArea;
    }
}
