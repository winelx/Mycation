package com.newdemo.winelx.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fm;
    public static ArrayList<String> ids;
    public static ArrayList<String> mData;


    public TabAdapter(FragmentManager fm, ArrayList<String> mData) {
        super(fm);
        this.mData = mData;
        ids = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int arg0) {
        TabFragment fragment = new TabFragment(arg0);
        return fragment;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }

    public void getAdate(ArrayList<String> ids) {
        this.ids = ids;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
