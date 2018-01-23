package com.newdemo.winelx.daynight2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.newdemo.winelx.daynight2.Pie.PieChartBeans;
import com.newdemo.winelx.daynight2.Pie.PieChartOne;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PieChartOne mPieChartOne;
    private List<PieChartBeans> mData;
    private Button add;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = new ArrayList<>();
        mPieChartOne = findViewById(R.id.pieChartOne);
        add = findViewById(R.id.add);
        mData.add(new PieChartBeans("时间", 15f, "#FFEC8B"));
        mData.add(new PieChartBeans("时间", 35f, "#FF1493"));
        mData.add(new PieChartBeans("时间", 25f, "#FF7256"));
        mData.add(new PieChartBeans("时间", 25f, "#EE5C42"));
        mPieChartOne.setDate(mData);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==1){
                    mData.clear();
                    mData.add(new PieChartBeans("时间", 15f, "#FFEC8B"));
                    mData.add(new PieChartBeans("时间", 25f, "#FF1493"));
                    mData.add(new PieChartBeans("时间", 10f, "#FF1493"));
                    mData.add(new PieChartBeans("时间", 25f, "#FF7256"));
                    mData.add(new PieChartBeans("时间", 25f, "#EE5C42"));
                    mPieChartOne.requestLayout();
                    mPieChartOne.setDate(mData);
                    i=2;
                }else if (i==2){
                    mData.clear();
                    mData.add(new PieChartBeans("时间", 10f, "#FFEC8B"));
                    mData.add(new PieChartBeans("时间", 20f, "#FF1493"));
                    mData.add(new PieChartBeans("时间", 10f, "#FF1493"));
                    mData.add(new PieChartBeans("时间", 20f, "#FF7256"));
                    mData.add(new PieChartBeans("时间", 20f, "#EE5C42"));
                    mData.add(new PieChartBeans("时间", 10f, "#FF1493"));
                    mData.add(new PieChartBeans("时间", 10f, "#FFEC8B"));
                    mPieChartOne.requestLayout();
                    mPieChartOne.setDate(mData);
                }

            }
        });
    }
}
