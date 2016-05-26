package com.exametric_administration.classes.realm_classes;

import com.exametric_administration.classes.classes.Area;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmArea {

    /**
     * Créer une zone à partir d'une chaine JSON
     * @param _realm Instance Realm
     * @param _json Chaine JSON
     */
    public static void createObjectFromJson(Realm _realm, String _json) {
        _realm.beginTransaction();
        _realm.createObjectFromJson(Area.class, _json);
        _realm.commitTransaction();
    }

    /**
     * Obtient toutes les zones de Realm
     * @param _realm Instance Realm
     * @return ArrayList de zone
     */
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

    /**
     * Obtient une zone à partir de son id
     * @param _realm Instance Realm
     * @param _idArea l'id de la zone
     * @return Objet Area
     */
    public static Area getAreaById(Realm _realm, int _idArea) {
        Area area = new Area();
        try {
            _realm.beginTransaction();
            area = _realm.where(Area.class).equalTo("idArea", _idArea).findFirst();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _realm.commitTransaction();
        }
        return area;
    }

    /**
     * Vide toutes les zones
     * @param _realm Instance Realm
     */
    public static void clearAreas(Realm _realm) {
        _realm.beginTransaction();
        _realm.delete(Area.class);
        _realm.commitTransaction();
    }

}
