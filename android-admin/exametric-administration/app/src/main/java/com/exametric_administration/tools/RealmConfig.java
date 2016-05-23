package com.exametric_administration.tools;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by boucherclement on 20/05/16.
 */
public class RealmConfig {

    public static RealmConfiguration realmConfiguration;
    public static Realm realmInstance;

    public static void configure(Context _context){
        realmConfiguration = new RealmConfiguration.Builder(_context).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realmInstance = Realm.getDefaultInstance();
    }

}
