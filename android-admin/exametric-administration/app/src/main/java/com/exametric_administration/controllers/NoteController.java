package com.exametric_administration.controllers;

import android.content.Context;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.R;
import com.exametric_administration.activities.NoteListView;
import com.exametric_administration.classes.classes.Note;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;

public class NoteController {
    /**
     * Télécharge toutes les notes d'une zone
     * @param _context Contexte de l'avtivité
     * @param _idArea L'id de la zone
     */
    public static void downloadNotesById(Context _context, int _idArea) {
        String url = GlobalVariables.BASE_URL + GlobalVariables.BY_ID_NOTES_URI+_idArea;
        RealmNote.clearNotes(RealmConfig.realmInstance);
        AQuery aq = new AQuery(_context);
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    JSONArray result = json.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        RealmNote.createObjectFromJson(RealmConfig.realmInstance, result.getString(i));
                    }
                    NoteListView.notifyDataChange(RealmNote.getAllNotes(RealmConfig.realmInstance));
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }
        });
    }

    /**
     * Envoie une note au serveur
     * @param _context Contexte de l'avtivité
     * @param _note La note à envoyer
     * @param _idArea L'id de la zone
     */
    public static void uploadNote(final Context _context, Note _note, final String _idArea) {
        String url = GlobalVariables.BASE_URL + GlobalVariables.UPLOAD_NOTE_URI;
        JSONObject json = new JSONObject();
        try {
            json.put("idNote", "");
            json.put("authorNote", _note.GetAuthorNote());
            json.put("textNote", _note.GetTextNote());
            json.put("dateNote", _note.GetDateNote());
            json.put("idArea", _idArea);
            System.out.println(json);
            AQuery aq = new AQuery(_context);
            aq.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    downloadNotesById(_context, Integer.valueOf(_idArea));
                }
            });
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
    }

}
