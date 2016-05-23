package com.exametric_administration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Context;
import com.exametric_administration.R;
import com.exametric_administration.classes.adapters.AreaAdapter;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.realm_classes.RealmArea;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.controllers.AreaController;
import com.exametric_administration.tools.RealmConfig;
import java.util.ArrayList;
import android.os.Handler;

public class AreaListView extends AppCompatActivity {
    private static ListView areasListview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_area);
        RealmConfig.configure(this);
        AreaController.downloadAllAreas(getBaseContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpAdapter(RealmArea.getAllAreas(RealmConfig.realmInstance));
            }
        }, 1000);
    }

    public void setUpAdapter(ArrayList<Area> _areas) {
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
        view.getContext().startActivity(intent);
    }

}
