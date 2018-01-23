package com.example.animation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import immortalz.me.library.TransitionsHeleper;


/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class PiechartActivity extends Activity {
    private PieChart mChart;
    private List<PieChartBean> mdata;
    private Context mContext;
    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = PiechartActivity.this;
        PieChart pieChart = (PieChart) findViewById(R.id.chart);
         img = findViewById(R.id.img);
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 10));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));

        PieDataSet dataset = new PieDataSet(entries, "# of Calls");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart.setDescription("");
        pieChart.setData(data);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionsHeleper.startAcitivty((Activity) mContext, RvDetailActivity.class,
                        img),
                        imgUrl);
            }
        });

    }
}