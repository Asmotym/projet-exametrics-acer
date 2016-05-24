package com.exametric_administration.activities;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.exametric_administration.R;
import com.exametric_administration.classes.adapters.AreaAdapter;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.controllers.AreaController;
import com.exametric_administration.tools.RealmConfig;
import java.util.ArrayList;
import android.os.Handler;
import android.widget.Toast;

public class AreaListView extends AppCompatActivity {
    private static ListView areasListview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_area);
        Toolbar toolbar = (Toolbar) findViewById(R.id.areaToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        RealmConfig.configure(this);
        AreaController.downloadAllAreas(getBaseContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpAreasAdapter(RealmArea.getAllAreas(RealmConfig.realmInstance));
            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
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

}

class OnAreaItemClickListener implements ListView.OnItemClickListener {
    private AreaAdapter areaAdapter;

    public OnAreaItemClickListener(AreaAdapter _areaAdapter) {
        this.areaAdapter = _areaAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RealmNote.clearNotes(RealmConfig.realmInstance);
        Area area = (Area) areaAdapter.getItem(position);
        Intent intent = new Intent(view.getContext(), NoteListView.class);
        intent.putExtra("idArea", area.GetIdArea());
        intent.putExtra("colorArea", area.GetColorArea());
        intent.putExtra("nameArea", area.GetNameArea());
        view.getContext().startActivity(intent);
    }

}
