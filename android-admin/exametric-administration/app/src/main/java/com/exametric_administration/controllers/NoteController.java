package com.exametric_administration.controllers;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoteController {

    public static void downloadNotesById(Context _context, int _idArea) {
        String url = GlobalVariables.BASE_URL+GlobalVariables.BY_ID_NOTES_URI+_idArea;
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
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }
        });
    }

}
