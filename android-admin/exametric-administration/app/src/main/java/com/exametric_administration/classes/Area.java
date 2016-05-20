package com.exametric_administration.classes;

public class Area {

    private int _idArea;
    private String _nameArea;
    private String _colorArea;

    public int GetIdArea() {
        return this._idArea;
    }

    public void SetIdArea(int idArea) {
        this._idArea = idArea;
    }

    public String GetNameArea() {
        return this._nameArea;
    }

    public void SetNameArea(String nameArea) {
        this._nameArea = nameArea;
    }

    public String GetColorArea() {
        return this._colorArea;
    }

    public void SetColorArea(String colorArea) {
        this._colorArea = colorArea;
    }

    public Area(int idArea, String nameArea, String colorArea) {
        SetIdArea(idArea);
        SetNameArea(nameArea);
        SetColorArea(colorArea);
    }

}
