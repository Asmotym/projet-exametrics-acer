package com.exametric_administration.activities;

import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.classes.Point;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener,
        View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private AQuery ajax;
    private AjaxCallback<JSONObject> cbAreas;
    private AjaxCallback<JSONObject> cbPointsByAreas;
    private AjaxCallback<JSONObject> cbLastArea;
    private AjaxCallback<JSONObject> cbAddArea;
    private AjaxCallback<JSONObject> cbAddPoints;
    private String urlAreas = "http://172.30.1.178:8080/exametrics-ws/areas";
    private String urlLastAreas = "http://172.30.1.178:8080/exametrics-ws/areas/last";
    private String urlPoints = "http://172.30.1.178:8080/exametrics-ws/points";
    private String urlPointsByArea = "http://172.30.1.178:8080/exametrics-ws/points/";
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
    private Switch switchMapType;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker currLocationMarker;
    private LatLng latLng;
    private int mZoom = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_edit);
        switchMapType = (Switch) findViewById(R.id.switchMap);
        switchMapType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    switchMapType.setText("Plan");
                    mMap.setMapType(2);
                } else {
                    switchMapType.setText("Satellite");
                    mMap.setMapType(1);
                }
            }
        });
        buttonEdit = (Button) findViewById(R.id.btnAddMap);
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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

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
                        area.SetIdArea(result.getJSONObject(j).getInt("idArea"));
                        area.SetNameArea(result.getJSONObject(j).getString("nameArea"));
                        area.SetColorArea(result.getJSONObject(j).getString("colorArea"));

                        cbPointsByAreas = new AjaxCallback<JSONObject>() {
                            public void callback(String urlPointsByAreas, JSONObject json, AjaxStatus status) {
                                polygonOptions = new PolygonOptions();
                                try {
                                    JSONArray result = json.getJSONArray("result");
                                    for (int i = 0; i < result.length(); i++) {
                                        point = new Point();
                                        point.SetIdPoint(result.getJSONObject(i).getInt("idPoint"));

                                        point.SetLongitude(Float.valueOf(result.getJSONObject(i).getString("longitude")));
                                        System.out.println("longitude : " + point.GetLongitude());

                                        point.SetLatitude(Float.valueOf(result.getJSONObject(i).getString("latitude")));
                                        System.out.println("latitude : " + point.GetLatitude());

                                        point.SetIdArea(result.getJSONObject(i).getInt("idArea"));
                                        polygonOptions.add(new LatLng(point.GetLongitude(), point.GetLatitude()));
                                        mMap.addMarker(new MarkerOptions().position(new LatLng(point.GetLongitude(), point.GetLatitude())));
                                        System.out.println(new LatLng(point.GetLongitude(), point.GetLatitude()));
                                        arraylistPoints.add(point);
                                    }
                                    for (int x = 0; x < arraylistAreas.size(); x++) {
                                        if (arraylistAreas.get(x).GetIdArea() == point.GetIdArea()) {
                                            polygonOptions.fillColor(Integer.decode(arraylistAreas.get(x).GetColorArea()));
                                        }
                                    }
                                    polygon = mMap.addPolygon(polygonOptions
                                            .strokeColor(0x00000000)
                                            .zIndex(100));
                                } catch (JSONException e) {
                                    System.out.println("PROBLEME JSON POINTS : " + e.getMessage());
                                }
                            }
                        };
                        ajax.ajax(urlPointsByArea + area.GetIdArea(), JSONObject.class, cbPointsByAreas);
                        arraylistAreas.add(area);
                    }
                } catch (JSONException e) {
                    System.out.println("PROBLEME JSON AREA : " + e.getMessage());
                }
            }
        };
        ajax.ajax(urlAreas, JSONObject.class, cbAreas);
        mMap.setMyLocationEnabled(true);

        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this,"buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(mZoom).build();

            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000); //1 second
        mLocationRequest.setFastestInterval(1000); //1 second
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onLocationChanged(Location location) {
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(mZoom).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    /*@Override
    public void onMyLocationChange(Location location) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title(getResources().getString(R.string.map_marker_name)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
    }*/

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

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

                        if (redHexValue.length() < 2 ){
                            redHexValue = "0" + redHexValue;
                        }
                        if (greenHexValue.length() < 2 ){
                            greenHexValue = "0" + greenHexValue;
                        }
                        if (blueHexValue.length() < 2 ){
                            blueHexValue = "0" + blueHexValue;
                        }


                        Area areaUpload = new Area();
                        areaUpload.SetColorArea(redHexValue + greenHexValue + blueHexValue);
                        areaUpload.SetNameArea(textName.getText().toString());
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
        cbLastArea = new AjaxCallback<JSONObject>() {
            public void callback(String url, JSONObject json, AjaxStatus status) {
                JSONObject area = new JSONObject();
                JSONObject point;
                JSONObject points = new JSONObject();


                polygonOptions = new PolygonOptions();
                try {
                    JSONArray result = json.getJSONArray("result");
                    area.put("idArea", result.getJSONObject(0).getInt("idArea") + 1);
                    area.put("nameArea", theArea.GetNameArea());
                    area.put("colorArea", "0x7f" + theArea.GetColorArea());
                    for (int j = 0; j < pointsLat.size(); j++) {
                        point = new JSONObject();
                        point.put("idPoint", "");
                        point.put("longitude", pointsLng.get(j));
                        point.put("latitude", pointsLat.get(j));
                        point.put("idArea", result.getJSONObject(0).getInt("idArea") + 1);
                        points.put("" + j, point);
                    }
                    final JSONObject addPoint = points;
                    cbAddArea = new AjaxCallback<JSONObject>() {
                        public void callback(String url, JSONObject json, AjaxStatus status) {

                            cbAddPoints = new AjaxCallback<JSONObject>() {
                                public void callback(String url, JSONObject json, AjaxStatus status) {

                                }
                            };
                            ajax.post(urlPoints, addPoint, JSONObject.class, cbAddPoints);
                        }
                    };


                    System.out.println(area);
                    System.out.println(points);
                    System.out.println(addPoint);

                    ajax.post(urlAreas, area, JSONObject.class, cbAddArea);


                } catch (JSONException e) {
                    System.out.println("PROBLEME JSON POINTS : " + e.getMessage());
                }
            }
        };
        ajax.ajax(urlLastAreas, JSONObject.class, cbLastArea);

    }
}
