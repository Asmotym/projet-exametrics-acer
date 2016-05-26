package com.exametric_administration.classes.realm_classes;

import com.exametric_administration.classes.classes.Point;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmPoint {

    /**
     * Créer un objet Point dans Realm à partir d'une chaine JSON
     * @param _realm Instance Realm
     * @param _json Chaine JSON
     */
    public static void createObjectFromJson(Realm _realm, String _json) {
        _realm.beginTransaction();
        _realm.createObjectFromJson(Point.class, _json);
        _realm.commitTransaction();
    }

    /**
     * Obtient tous les points dans Realm
     * @param _realm Instance Realm
     * @return ArrayList d'objet Point
     */
    public static ArrayList<Point> getAllPoints(Realm _realm) {
        _realm.beginTransaction();
        ArrayList<Point> points = new ArrayList<>();
        RealmResults<Point> result = _realm.where(Point.class).findAll();
        for (Point point : result) {
            points.add(point);
        }
        _realm.commitTransaction();
        return points;
    }

    /**
     * Obtient tous les points d'une zone définie
     * Get all points from a defined area
     * @param _realm Instance Realm
     * @param _idArea Id de la zone
     * @return ArrayList d'objet Point
     */
    public static ArrayList<Point> getPointsByAreaId(Realm _realm, int _idArea) {
        _realm.beginTransaction();
        ArrayList<Point> points = new ArrayList<>();
        RealmResults<Point> result = _realm.where(Point.class).equalTo("idArea", _idArea).findAll();
        for (Point point : result) {
            points.add(point);
        }
        _realm.commitTransaction();
        return points;
    }

    /**
     * Vide tous les objets point de Realm
     * @param _realm Instance Realm
     */
    public static void clearPoints(Realm _realm) {
        _realm.beginTransaction();
        _realm.delete(Point.class);
        _realm.commitTransaction();
    }

}
