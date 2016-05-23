package com.gaming.beehaku.myapplication.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beehaku on 19/05/2016.
 */
public class Area  {

    private int _id;
    private String _name;
    private String _color;
    private ArrayList<Point> _points;

    public Area() {

    }

    public Area(int _id, String _name, String _color, ArrayList<Point> _points) {
        this._id = _id;
        this._name = _name;
        this._color = _color;
        this._points = _points;
    }

    public int GetId() {
        return _id;
    }

    public void SetId(int _id) {
        this._id = _id;
    }

    public String GetName() {
        return _name;
    }

    public void SetName(String _name) {
        this._name = _name;
    }

    public String GetColor() {
        return _color;
    }

    public void SetColor(String _color) {
        this._color = _color;
    }

    public ArrayList<Point> GetPoints() {
        return _points;
    }

    public void SetPoints(ArrayList<Point> _points) {
        this._points = _points;
    }
}
