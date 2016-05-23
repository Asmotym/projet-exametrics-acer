package com.gaming.beehaku.myapplication.Classes;

/**
 * Created by Beehaku on 19/05/2016.
 */
public class Point {

    private int _id;
    private Float _latitude;
    private Float _longitude;
    private int theArea;

    public Point(int _id, Float _latitude, Float _longitude, int theArea) {
        this._id = _id;
        this._latitude = _latitude;
        this._longitude = _longitude;
        this.theArea = theArea;
    }

    public Point() {

    }

    public int GetId() {
        return _id;
    }

    public void SetId(int _id) {
        this._id = _id;
    }

    public Float GetLatitude() {
        return _latitude;
    }

    public void SetLatitude(Float _latitude) {
        this._latitude = _latitude;
    }

    public Float GetLongitude() {
        return _longitude;
    }

    public void SetLongitude(Float _longitude) {
        this._longitude = _longitude;
    }

    public int GetTheArea() {
        return theArea;
    }

    public void SetTheArea(int theArea) {
        this.theArea = theArea;
    }
}
