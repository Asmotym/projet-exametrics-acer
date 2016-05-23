package com.exametric_administration.controllers;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AreaController {

    public static void downloadAllAreas(Context _context) {
        String url = GlobalVariables.BASE_URL+GlobalVariables.AREAS_URI;
        RealmArea.clearAreas(RealmConfig.realmInstance);
        AQuery aq = new AQuery(_context);
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    JSONArray result = json.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        RealmArea.createObjectFromJson(RealmConfig.realmInstance, result.getString(i));
                    }
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }

        });
    }

}
