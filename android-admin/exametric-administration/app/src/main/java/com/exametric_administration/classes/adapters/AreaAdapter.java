package com.exametric_administration.classes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.exametric_administration.R;
import com.exametric_administration.classes.classes.Area;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Adapteur pour la liste des zones
 */
public class AreaAdapter extends BaseAdapter {
    private ArrayList<Area> areas;
    LayoutInflater inflater;
    Context context;
    TextView areaNameTextView;
    FrameLayout areaItemLayout;

    public AreaAdapter(Context _context, ArrayList<Area> _areas) {
        areas = _areas;
        context = _context;
        inflater = LayoutInflater.from(_context);
    }

    @Override
    public int getCount() {
        return areas.size();
    }

    @Override
    public Object getItem(int position) {
        return areas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_area_item, parent, false);
        }
        Area area = areas.get(position);

        areaNameTextView = (TextView) convertView.findViewById(R.id.areaNameTextView);
        areaItemLayout = (FrameLayout) convertView.findViewById(R.id.areaFrameLayoutBackground);

        areaNameTextView.setText(area.GetNameArea());
        areaItemLayout.setBackgroundColor(Integer.decode(area.GetColorArea()));

        return convertView;
    }

    public void setData(ArrayList<Area> _areas) {
        this.areas = _areas;
        this.notifyDataSetChanged();
    }

}
