package com.exametric_administration.classes.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by boucherclement on 20/05/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static ArrayList<Fragment> fragmentsList;
    private static ArrayList<String> fragmentsTitle;

    public ViewPagerAdapter(FragmentManager _fm) {
        super(_fm);
        fragmentsTitle = new ArrayList<>();
        fragmentsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }

    public void addFragment(Fragment _fragment, String _title){
        fragmentsList.add(_fragment);
        fragmentsTitle.add(_title);
    }

}
