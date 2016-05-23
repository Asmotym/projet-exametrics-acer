package com.exametric_administration.classes.classes;

import io.realm.RealmObject;

public class Point extends RealmObject {

    private int _idPoint;
    private double _longitude;
    private double _latitude;


    public int GetIdPoint() {
        return _idPoint;
    }

    public void SetIdPoint(int idPoint) {
        this._idPoint = idPoint;
    }

    public double GetLongitude() {
        return _longitude;
    }

    public void SetLongitude(double longitude) {
        this._longitude = longitude;
    }

    public double GetLatitude() {
        return _latitude;
    }

    public void SetLatitude(double latitude) {
        this._latitude = latitude;
    }

    public Point() {}

    public Point(int idPoint, double longitude, double latitude) {
        SetIdPoint(idPoint);
        SetLongitude(longitude);
        SetLatitude(latitude);
    }

}
