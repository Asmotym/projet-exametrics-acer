package com.exametric_administration.classes.classes;

import java.io.Serializable;

import io.realm.RealmObject;

public class Area extends RealmObject {

    private int idArea;
    private String nameArea;
    private String colorArea;

    public int GetIdArea() {
        return this.idArea;
    }

    public void SetIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String GetNameArea() {
        return this.nameArea;
    }

    public void SetNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public String GetColorArea() {
        return this.colorArea;
    }

    public void SetColorArea(String colorArea) {
        this.colorArea = colorArea;
    }

    public Area() {}

    public Area(int idArea, String nameArea, String colorArea) {
        SetIdArea(idArea);
        SetNameArea(nameArea);
        SetColorArea(colorArea);
    }

}
