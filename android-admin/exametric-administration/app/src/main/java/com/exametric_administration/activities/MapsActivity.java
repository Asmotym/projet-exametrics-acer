package com.exametric_administration.activities;

import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
import android.os.Handler;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.classes.Point;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmPoint;
import com.exametric_administration.controllers.AreaController;
import com.exametric_administration.controllers.PointController;
import com.exametric_administration.tools.RealmConfig;
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

    private static GoogleMap mMap;
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
    private static Polygon polygon;
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
    private static Double lat;
    private static Double lng;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_edit);
        context = getBaseContext();
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
                    arrayLat.clear();
                    arrayLng.clear();
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
        mMap.clear();
        arraylistAreas = new ArrayList<>();
        arraylistPoints = new ArrayList<>();
        arrayLat = new ArrayList<>();
        arrayLng = new ArrayList<>();
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
<<<<<<< Updated upstream


=======
        ArrayList<Area> areas = RealmArea.getAllAreas(RealmConfig.realmInstance);
        PointController.getAllPoints(getBaseContext());
>>>>>>> Stashed changes

        mMap.setMyLocationEnabled(true);

        buildGoogleApiClient();
        mGoogleApiClient.connect();

        PointController.getAllPoints(context);
    }

    public static void setAllMapPoints(ArrayList<Area> _areas) {
        for (int i = 0; i < _areas.size(); i++) {
            ArrayList<LatLng> latlng = new ArrayList<LatLng>();
            lat = 0.0;
            lng = 0.0;
            PolygonOptions polygonOptions = new PolygonOptions();
            ArrayList<Point> areaPoints = RealmPoint.getPointsByAreaId(RealmConfig.realmInstance, _areas.get(i).GetIdArea());
            for (int j = 0; j < areaPoints.size(); j++) {
                latlng.add(new LatLng(areaPoints.get(j).GetLatitude(), areaPoints.get(j).GetLongitude()));
                //polygonOptions.add(new LatLng(areaPoints.get(j).GetLatitude(), areaPoints.get(j).GetLongitude()));
                //mMap.addMarker(new MarkerOptions().position(new LatLng(areaPoints.get(j).GetLatitude(), areaPoints.get(j).GetLongitude())));
                lat += areaPoints.get(j).GetLatitude();
                lng += areaPoints.get(j).GetLongitude();
            }
            mMap.addPolygon(polygonOptions.strokeColor(Integer.decode(_areas.get(i).GetColorArea())).zIndex(100).addAll(latlng));

            addText(context, mMap, new LatLng(lat / areaPoints.size(), lng / areaPoints.size()), _areas.get(i).GetNameArea(), 0, 30);


        }
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
                                .strokeColor(0xaa000000)
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


                        Area area = new Area(0, textName.getText().toString(), redHexValue + greenHexValue + blueHexValue);
                        ArrayList<Point> points = new ArrayList<>();
                        for (int i = 0; i < arrayLat.size(); i++) {
                            points.add(new Point(0, arrayLng.get(i), arrayLat.get(i), area.GetIdArea()));
                        }
                        AreaController.uploadArea(getApplicationContext(), area, points);
                        arrayLat.clear();
                        arrayLng.clear();
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

    public static Marker addText(final Context context, final GoogleMap map, final LatLng location, final String text, final int padding, final int fontSize) {
        Marker marker = null;

        if (context == null || map == null || location == null || text == null
                || fontSize <= 0) {
            return marker;
        }

        final TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(fontSize);

        final Paint paintText = textView.getPaint();

        final Rect boundsText = new Rect();
        paintText.getTextBounds(text, 0, textView.length(), boundsText);
        paintText.setTextAlign(Paint.Align.CENTER);

        final Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        final Bitmap bmpText = Bitmap.createBitmap(boundsText.width() + 2
                * padding, boundsText.height() + 2 * padding, conf);

        final Canvas canvasText = new Canvas(bmpText);
        paintText.setColor(Color.BLACK);

        canvasText.drawText(text, canvasText.getWidth() / 2,
                canvasText.getHeight() - padding, paintText);

        final MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromBitmap(bmpText))
                .anchor(0.5f, 1);

        marker = map.addMarker(markerOptions);

        return marker;
    }
}
