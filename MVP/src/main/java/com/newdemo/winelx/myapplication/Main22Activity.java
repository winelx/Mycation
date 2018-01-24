package com.newdemo.winelx.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main22Activity extends AppCompatActivity {
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private List<String> list4 = new ArrayList<>();
    private List<bean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        list1.add("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
        list2.add("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
        list2.add("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
        list3.add("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
        list3.add("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
        list3.add("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg");

//        for (int i = 0; i < 5; i++) {
////            if (i==1){
////                mData.add(new bean(list4));
////            }
//            mData.add(new bean(list1));
//            mData.add(new bean(list2));
//            mData.add(new bean(list3));
//        }
//        RecyclerView listView = (RecyclerView) findViewById(R.id.list_view);
//        listView.setLayoutManager(new LinearLayoutManager(this));
//        recyeclerAdapter reac = new recyeclerAdapter(Main22Activity.this, mData);
//        listView.setAdapter(reac);

        ListView    listView = (ListView) findViewById(R.id.list_view);
        ImageAdapter adapter = new ImageAdapter(this, 0, Images.imageUrls);
        listView.setAdapter(adapter);
    }
}
