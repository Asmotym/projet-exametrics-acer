package com.exametric_administration.activities;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.exametric_administration.R;
import com.exametric_administration.classes.adapters.AreaAdapter;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.click_listeners.OnAreaItemClickListener;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.controllers.AreaController;
import com.exametric_administration.tools.RealmConfig;
import java.util.ArrayList;

public class AreaListView extends AppCompatActivity {
    private static ListView areasListview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_area);
        // on configure la toolbar
        setUpActionBar();
        // on configure Realm
        RealmConfig.configure(this);
        // on télécharge toutes les zones
        AreaController.downloadAllAreas(getBaseContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpAreasAdapter(RealmArea.getAllAreas(RealmConfig.realmInstance));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setUpActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.areaToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuReload:
                AreaController.downloadAllAreas(getBaseContext());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUpAreasAdapter(ArrayList<Area> _areas) {
        areasListview = (ListView) findViewById(R.id.areaListView);
        AreaAdapter areaAdapter = new AreaAdapter(getBaseContext(), _areas);
        areasListview.setAdapter(areaAdapter);
        areasListview.setOnItemClickListener(new OnAreaItemClickListener(areaAdapter));
    }

    public static void notifyDataChange(ArrayList<Area> _areas) {
        AreaAdapter areaAdapter = (AreaAdapter) areasListview.getAdapter();
        areaAdapter.setData(_areas);
    }

}
