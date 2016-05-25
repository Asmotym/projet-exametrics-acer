package com.exametric_administration.controllers;

import android.content.Context;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.activities.AreaListView;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.classes.Point;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AreaController {

    /**
     * Download all areas from the Database using the Web Service
     * @param _context
     */
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
                    AreaListView.notifyDataChange(RealmArea.getAllAreas(RealmConfig.realmInstance));
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }
        });
    }

    public static void uploadArea(final Context _context, final Area _area, final ArrayList<Point> _points) {
        String url = GlobalVariables.BASE_URL + GlobalVariables.UPLOAD_AREA_URI;
        AQuery aq = new AQuery(_context);
        JSONObject json = new JSONObject();
        try {
            json.put("idArea", "");
            json.put("nameArea", _area.GetNameArea());
            json.put("colorArea", "0x7f" + _area.GetColorArea());
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        aq.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    JSONArray result = json.getJSONArray("result");
                    JSONObject object = result.getJSONObject(0);
                    JSONObject points = new JSONObject();
                    for (int i = 0; i < _points.size(); i++) {
                        JSONObject point = new JSONObject();
                        point.put("idPoint", "");
                        point.put("longitude", _points.get(i).GetLongitude());
                        point.put("latitude", _points.get(i).GetLatitude());
                        point.put("idArea", object.getString("idArea"));
                        points.put(String.valueOf(i), point);
                    }
                    PointController.uploadPoints(_context, points);
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }
        });

    }

}
