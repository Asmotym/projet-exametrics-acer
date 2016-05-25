package com.exametric_administration.classes.classes;

import io.realm.RealmObject;

public class Point extends RealmObject {

    private int idPoint;
    private Double longitude;
    private Double latitude;
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

    public Double GetLongitude() {
        return longitude;
    }

    public void SetLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double GetLatitude() {
        return latitude;
    }

    public void SetLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Point() {}

    public Point(int idPoint, Double longitude, Double latitude, int idArea) {
        SetIdPoint(idPoint);
        SetLongitude(longitude);
        SetLatitude(latitude);
        SetIdArea(idArea);
    }

}
