package com.exametric_administration.classes.realm_classes;

import com.exametric_administration.classes.classes.Note;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmNote {

    public static void copyToRealm(Realm _realm, Note _note) {
        _realm.beginTransaction();
        _realm.copyToRealm(_note);
        _realm.commitTransaction();
    }

    public static void createObjectFromJson(Realm _realm, String _json) {
        _realm.beginTransaction();
        _realm.createObjectFromJson(Note.class, _json);
        _realm.commitTransaction();
    }

    public static ArrayList<Note> getNotesByAreaId(Realm _realm) {
        _realm.beginTransaction();
        ArrayList<Note> notes = new ArrayList<>();
        RealmResults<Note> results = _realm.where(Note.class).findAll();
        if (results.size() == 0) {
            _realm.commitTransaction();
            return notes;
        } else {
            for (Note note: notes) {
                notes.add(note);
                System.out.println(note.GetTextNote());
            }
            _realm.commitTransaction();
            return notes;
        }
    }

    public static void clearNotes(Realm _realm) {
        _realm.beginTransaction();
        _realm.delete(Note.class);
        _realm.commitTransaction();
    }

}
