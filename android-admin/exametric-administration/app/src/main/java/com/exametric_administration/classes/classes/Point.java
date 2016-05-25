package com.exametric_administration.classes.classes;

import io.realm.RealmObject;

public class Point extends RealmObject {

    private int idPoint;
    private String longitude;
    private String latitude;
    private int idArea;

    public int GetIdArea() {
        return idArea;
    }

    public void SetIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int GetIdPoint() {
        return idPoint;
    }

    public void SetIdPoint(int idPoint) {
        this.idPoint = idPoint;
    }

    public String GetLongitude() {
        return longitude;
    }

    public void SetLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String GetLatitude() {
        return latitude;
    }

    public void SetLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Point() {}

    public Point(int idPoint, String longitude, String latitude, int idArea) {
        SetIdPoint(idPoint);
        SetLongitude(longitude);
        SetLatitude(latitude);
        SetIdArea(idArea);

    }

}
