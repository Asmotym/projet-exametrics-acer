package com.exametric_administration.tools;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Build a realm instance with this class.
 * You can access to the realm instance with the static realmInstance field everywhere.
 */
public class RealmConfig {
    static RealmConfiguration realmConfiguration;
    public static Realm realmInstance;

    /**
     * Build a RealmConfiguration and instanciate a Realm.
     * @param _context Context of the application
     */
    public static void configure(Context _context){
        realmConfiguration = new RealmConfiguration.Builder(_context).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realmInstance = Realm.getDefaultInstance();
    }

}
