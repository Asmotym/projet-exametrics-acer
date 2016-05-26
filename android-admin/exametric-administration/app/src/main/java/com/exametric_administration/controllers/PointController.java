package com.exametric_administration.controllers;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.activities.MapsActivity;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmPoint;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PointController {

    /**
     * Télécharge tous les points d'une zone définie
     * @param _context Contexte de l'avtivité
     */
    public static void getAllPoints(Context _context) {
        String url = GlobalVariables.BASE_URL + GlobalVariables.POINTS_URI;
        AQuery aq = new AQuery(_context);
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    JSONArray result = json.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        RealmPoint.createObjectFromJson(RealmConfig.realmInstance, result.getString(i));
                    }
                    MapsActivity.setAllMapPoints(RealmArea.getAllAreas(RealmConfig.realmInstance));
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }
        });
    }

    /**
     * Envoie une liste de points au serveur
     * @param _context Contexte de l'avtivité
     * @param _points JSON Objet qui contients tous les points
     */
    public static void uploadPoints(final Context _context, JSONObject _points) {
        String url = GlobalVariables.BASE_URL + GlobalVariables.UPLOAD_POINTS_URI;
        AQuery aq = new AQuery(_context);
        aq.post(url, _points, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                AreaController.downloadAllAreas(_context);
            }
        });
    }

}
