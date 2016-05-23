package com.gaming.beehaku.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.gaming.beehaku.myapplication.Classes.Area;
import com.gaming.beehaku.myapplication.Classes.Point;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {

    private GoogleMap mMap;
    private AQuery ajax;
    private AjaxCallback<JSONObject> cbAreas;
    private AjaxCallback<JSONObject> cbPointsByAreas;
    private AjaxCallback<JSONObject> cbLastArea;
    private AjaxCallback<JSONObject> cb;
    private String urlAreas = "http://172.30.1.178:8080/exametrics-ws/areas";
    private String urlLastAreas = "http://172.30.1.178:8080/exametrics-ws/areas/last";
    private String urlPoints = "http://172.30.1.178:8080/exametrics-ws/points";
    private String urlPointsByArea = "http://172.30.1.178:8080/exametrics-ws/points/";
    private String urlNotes = "http://172.30.1.178:8080/exametrics-ws/notes";
    private String urlNotesByArea = "http://172.30.1.178:8080/exametrics-ws/notes/";
    private Area area;
    private Point point;
    private ArrayList<Area> arraylistAreas;
    private ArrayList<Point> arraylistPoints;
    private PolygonOptions polygonOptions;
    private Polygon polygon;
    private Boolean isEditing = false;
    private Button buttonEdit;
    private ArrayList<Marker> markerList;
    private ArrayList<Double> arrayLat;
    private ArrayList<Double> arrayLng;
    private Marker marker;
    private SeekBar seek1;
    private SeekBar seek2;
    private SeekBar seek3;
    public int idLastArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_edit);

        buttonEdit = (Button) findViewById(R.id.btnAddMap);
        buttonEdit.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                mMap.clear();
                return true;
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isEditing == false) {

                    buttonEdit.setText("✔");
                    markerList = new ArrayList<Marker>();
                    isEditing = true;
                } else {

                    if (arrayLat.size() >= 3) {

                        ShowDialog();

                    }
                    for (Marker theMarker : markerList) {
                        theMarker.remove();
                    }
                    markerList.clear();
                    buttonEdit.setText("+");
                    isEditing = false;


                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ajax = new AQuery(this);
        mMap = googleMap;
        arraylistAreas = new ArrayList<Area>();
        arraylistPoints = new ArrayList<Point>();
        arrayLat = new ArrayList<Double>();
        arrayLng = new ArrayList<Double>();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {

                if (isEditing == true) {

                    arrayLat.add(arg0.latitude);
                    arrayLng.add(arg0.longitude);
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(arg0.latitude, arg0.longitude)));
                    markerList.add(marker);
                }

            }
        });

        cbAreas = new AjaxCallback<JSONObject>() {
            public void callback(String urlAreas, JSONObject json, AjaxStatus status) {
                try {
                    JSONArray result = json.getJSONArray("result");
                    for (int j = 0; j < result.length(); j++) {
                        area = new Area();
                        area.SetId(result.getJSONObject(j).getInt("idArea"));
                        area.SetName(result.getJSONObject(j).getString("nameArea"));
                        area.SetColor(result.getJSONObject(j).getString("colorArea"));


                        cbPointsByAreas = new AjaxCallback<JSONObject>() {
                            public void callback(String urlPointsByAreas, JSONObject json, AjaxStatus status) {

                                polygonOptions = new PolygonOptions();
                                try {
                                    JSONArray result = json.getJSONArray("result");

                                    for (int i = 0; i < result.length(); i++) {
                                        point = new Point();
                                        point.SetId(result.getJSONObject(i).getInt("idPoint"));
                                        point.SetLongitude(Float.parseFloat(result.getJSONObject(i).getString("longitude")));
                                        point.SetLatitude(Float.parseFloat(result.getJSONObject(i).getString("latitude")));
                                        point.SetTheArea(result.getJSONObject(i).getInt("idArea"));

                                        polygonOptions.add(new LatLng(point.GetLongitude(), point.GetLatitude()));
                                        arraylistPoints.add(point);

                                    }
                                    for (int x = 0; x < arraylistAreas.size(); x++) {
                                        if (arraylistAreas.get(x).GetId() == point.GetTheArea()) {
                                            polygonOptions.fillColor(Integer.decode("0x7f" + arraylistAreas.get(x).GetColor()));
                                        }
                                    }
                                    polygon = mMap.addPolygon(polygonOptions
                                            .strokeColor(0x00000000)
                                            .zIndex(100));
                                    area.SetPoints(arraylistPoints);

                                } catch (JSONException e) {
                                    System.out.println("PROBLEME JSON POINTS : " + e.getMessage());
                                }
                            }
                        };

                        ajax.ajax(urlPointsByArea + area.GetId(), JSONObject.class, cbPointsByAreas);
                        arraylistAreas.add(area);
                    }

                } catch (JSONException e) {
                    System.out.println("PROBLEME JSON AREA : " + e.getMessage());
                }
            }
        };

        ajax.ajax(urlAreas, JSONObject.class, cbAreas);

        // Add a marker in Sydney and move the camera
        LatLng imerir = new LatLng(42.66, 2.82);
        mMap.addMarker(new MarkerOptions().position(imerir).title("Marker on IMERIR"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(imerir.latitude, imerir.longitude), 12.0f));

        // Instantiates a new Polygon object and adds points to define a rectangle
        /*Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(42, 2), new LatLng(43, 2), new LatLng(43, 3), new LatLng(42, 3))
                .fillColor(0x7F121212)
        .strokeColor(0x00000000)
        .zIndex(100));*/
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onClick(View v) {

    }

    public void ShowDialog() {

        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        final View Viewlayout = inflater.inflate(R.layout.activity_dialog,
                (ViewGroup) findViewById(R.id.layout_dialog));

        final TextView item1 = (TextView) Viewlayout.findViewById(R.id.txtRed); // txtItem1
        final TextView item2 = (TextView) Viewlayout.findViewById(R.id.txtGreen); // txtItem2

        final TextView item3 = (TextView) Viewlayout.findViewById(R.id.txtBlue); // txtItem2
        final EditText textName = (EditText) Viewlayout.findViewById(R.id.editText);
        popDialog.setTitle("Finalisation");
        popDialog.setView(Viewlayout);

        final TextView btnColor = (TextView) Viewlayout.findViewById(R.id.btnColor);

        seek1 = (SeekBar) Viewlayout.findViewById(R.id.seekBar1);
        seek2 = (SeekBar) Viewlayout.findViewById(R.id.seekBar2);
        seek3 = (SeekBar) Viewlayout.findViewById(R.id.seekBar3);

        //  seekBar1
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Do something here with new value
                btnColor.setBackgroundColor(Color.rgb(seek1.getProgress(), seek2.getProgress(), seek3.getProgress()));
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        //  seekBar2
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Do something here with new value
                btnColor.setBackgroundColor(Color.rgb(seek1.getProgress(), seek2.getProgress(), seek3.getProgress()));
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        //  seekBar3
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Do something here with new value
                btnColor.setBackgroundColor(Color.rgb(seek1.getProgress(), seek2.getProgress(), seek3.getProgress()));
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        // Button OK
        popDialog.setPositiveButton("Valider",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        polygonOptions = new PolygonOptions();
                        for (int i = 0; i < arrayLat.size(); i++) {
                            polygonOptions.add(new LatLng(arrayLat.get(i), arrayLng.get(i)));
                        }

                        polygon = mMap.addPolygon(polygonOptions
                                .fillColor(Color.argb(128, seek1.getProgress(), seek2.getProgress(), seek3.getProgress()))
                                .strokeColor(0x00000000)
                                .zIndex(100));

                        String redHexValue = Integer.toHexString(seek1.getProgress());
                        String greenHexValue = Integer.toHexString(seek2.getProgress());
                        String blueHexValue = Integer.toHexString(seek3.getProgress());

                        Area areaUpload = new Area();
                        areaUpload.SetColor(redHexValue + greenHexValue + blueHexValue);
                        areaUpload.SetName(textName.getText().toString());

                        uploadArea(areaUpload, arrayLat, arrayLng);


                    }

                });

        popDialog.setNegativeButton("Annuler",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        arrayLng.clear();
                        arrayLat.clear();
                        dialog.dismiss();
                    }
                }

        );

        popDialog.create();
        popDialog.show();

    }

    public void uploadArea(final Area theArea, final ArrayList<Double> pointsLat, final ArrayList<Double> pointsLng) {

        final JSONObject area = new JSONObject();
        final JSONObject points = new JSONObject();
        final JSONObject point = new JSONObject();



        cbLastArea = new AjaxCallback<JSONObject>() {
            public void callback(String url, JSONObject json, AjaxStatus status) {

                cb = new AjaxCallback<JSONObject>() {
                    public void callback(String url, JSONObject json, AjaxStatus status) {


                        arrayLng.clear();
                        arrayLat.clear();

                    }
                };

                polygonOptions = new PolygonOptions();
                try {
                    JSONArray result = json.getJSONArray("result");

                    area.put("idArea", result.getJSONObject(0).getInt("idArea") + 1);
                    area.put("nameArea", theArea.GetName());
                    area.put("colorArea", theArea.GetColor());

                    for (int j = 0; j < pointsLat.size(); j++) {

                        point.put("idPoint", "");
                        point.put("longitude", pointsLng.get(j));

                        point.put("latitude", pointsLat.get(j));
                        point.put("idArea", result.getJSONObject(0).getInt("idArea") + 1);

                        points.put("" + j, point);

                    }
                    System.out.println("points " + points);
                    System.out.println("area " + area);
                    ajax.post(urlPoints, points, JSONObject.class, cb);
                    ajax.post(urlAreas, area, JSONObject.class, cb);


                } catch (JSONException e) {
                    System.out.println("PROBLEME JSON POINTS : " + e.getMessage());
                }
            }
        };

        ajax.ajax(urlLastAreas, JSONObject.class, cbLastArea);

    }

}