package com.exametric_administration.tools;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Permet de construire une instance de la classe Realm et de l'utiliser en statique partout grâce au champ realmInstance
 */
public class RealmConfig {
    static RealmConfiguration realmConfiguration;
    public static Realm realmInstance;

    /**
     * Construit une RealmConfiguration et instancie Realm.
     * @param _context Contexte de l'application
     */
    public static void configure(Context _context){
        realmConfiguration = new RealmConfiguration.Builder(_context).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realmInstance = Realm.getDefaultInstance();
    }

}
