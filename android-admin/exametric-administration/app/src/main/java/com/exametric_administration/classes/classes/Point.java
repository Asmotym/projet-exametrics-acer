package com.exametric_administration.classes.classes;

import io.realm.RealmObject;

public class Point extends RealmObject {

    private int idPoint;
    private float longitude;
    private float latitude;
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

    public float GetLongitude() {
        return longitude;
    }

    public void SetLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float GetLatitude() {
        return latitude;
    }

    public void SetLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Point() {}

    public Point(int idPoint, float longitude, float latitude, int idArea) {
        SetIdPoint(idPoint);
        SetLongitude(longitude);
        SetLatitude(latitude);
        SetIdArea(idArea);

    }

}
