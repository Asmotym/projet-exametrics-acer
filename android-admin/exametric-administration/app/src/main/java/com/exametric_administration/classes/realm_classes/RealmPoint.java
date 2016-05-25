package com.exametric_administration.classes.realm_classes;

import com.exametric_administration.classes.classes.Point;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmPoint {

    public static void copyToRealm(Realm _realm, Point _note) {
        _realm.beginTransaction();
        _realm.copyToRealm(_note);
        _realm.commitTransaction();
    }

    public static void createObjectFromJson(Realm _realm, String _json) {
        _realm.beginTransaction();
        _realm.createObjectFromJson(Point.class, _json);
        _realm.commitTransaction();
    }

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

    public static void clearPoints(Realm _realm) {
        _realm.beginTransaction();
        _realm.delete(Point.class);
        _realm.commitTransaction();
    }

}
