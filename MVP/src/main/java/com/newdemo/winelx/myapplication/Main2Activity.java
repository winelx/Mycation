package com.newdemo.winelx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager viewpager;
    private TabAdapter mAdapter;
    private ArrayList<String> mData;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mData = new ArrayList();
        Intent intent = getIntent();
        mData = intent.getExtras().getStringArrayList("data");
        pos = intent.getExtras().getInt("pos");
        viewpager = findViewById(R.id.viewpager);
        mAdapter = new TabAdapter(getSupportFragmentManager(), mData);
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(pos);
    }

    void Toast(String str) {
        Toast.makeText(Main2Activity.this, str, Toast.LENGTH_SHORT).show();
    }

}
