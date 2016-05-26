package com.exametric_administration.classes.click_listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.exametric_administration.activities.NoteListView;
import com.exametric_administration.classes.adapters.AreaAdapter;
import com.exametric_administration.classes.classes.Area;
import com.exametric_administration.classes.realm_classes.RealmNote;
import com.exametric_administration.tools.GlobalVariables;
import com.exametric_administration.tools.RealmConfig;


public class OnAreaItemClickListener implements ListView.OnItemClickListener {
    private AreaAdapter areaAdapter;

    public OnAreaItemClickListener(AreaAdapter _areaAdapter) {
        this.areaAdapter = _areaAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Ont set la variable Actual area pour éviter les bugs plus tard et ont envoie l'id de l'area en extra
        GlobalVariables.ACTUAL_AREA = 0;
        RealmNote.clearNotes(RealmConfig.realmInstance);
        Area area = (Area) areaAdapter.getItem(position);
        Intent intent = new Intent(view.getContext(), NoteListView.class);
        intent.putExtra("idArea", area.GetIdArea());
        view.getContext().startActivity(intent);
    }

}
