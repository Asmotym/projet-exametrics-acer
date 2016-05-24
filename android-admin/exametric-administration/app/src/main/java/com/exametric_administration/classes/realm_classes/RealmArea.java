package com.exametric_administration.classes.realm_classes;

import com.exametric_administration.classes.classes.Area;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmArea {

    public static void copyToRealm(Realm _realm, Area _area) {
        _realm.beginTransaction();
        _realm.copyToRealm(_area);
        _realm.commitTransaction();
    }

    public static void createObjectFromJson(Realm _realm, String _json) {
        _realm.beginTransaction();
        _realm.createObjectFromJson(Area.class, _json);
        _realm.commitTransaction();
    }

    public static ArrayList<Area> getAllAreas(Realm _realm) {
        _realm.beginTransaction();
        ArrayList<Area> areas = new ArrayList<>();
        RealmResults<Area> results = _realm.where(Area.class).findAll();
        if (results.size() == 0) {
            _realm.commitTransaction();
            return areas;
        } else {
            for (Area area: results) {
                areas.add(area);
            }
            _realm.commitTransaction();
            return areas;
        }
    }

    public static Area getAreaById(Realm _realm, int _idArea) {
        _realm.beginTransaction();
        Area area = _realm.where(Area.class).equalTo("idArea", _idArea).findFirst();
        _realm.commitTransaction();
        return area;
    }

    public static void clearAreas(Realm _realm) {
        _realm.beginTransaction();
        _realm.delete(Area.class);
        _realm.commitTransaction();
    }

}
