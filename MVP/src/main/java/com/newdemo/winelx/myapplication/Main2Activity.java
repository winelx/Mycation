package com.newdemo.winelx.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private ImageView imageView;
    private View popupWindowView;
    private PopupWindow popupWindow;
    private String status = "no";
    private SwipeRefreshLayout Swip;
    private ListView mlListView;
    private PopupWindow mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.meun);
        Swip = findViewById(R.id.Swip);
        findViewById(R.id.tiaozhuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Main2Activity.this, Main22Activity.class));
            }
        });
        Swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onRefresh() {
                switch (status) {
                    case "all":
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Main2Activity.this, "全部", Toast.LENGTH_SHORT).show();
                                dis();
                            }
                        }, 2000);
                        break;
                    case "yes":
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Main2Activity.this, "已完成", Toast.LENGTH_SHORT).show();
                                dis();
                            }
                        }, 2000);
                        break;
                    case "no":
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Main2Activity.this, "未完成", Toast.LENGTH_SHORT).show();
                                dis();
                            }
                        }, 2000);
                        break;
                }
            }
        });
        mlListView = findViewById(R.id.list_one);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(Main2Activity.this).inflate(R.layout.popupwindow, null);
                mPopWindow = new PopupWindow(contentView);
                mPopWindow.setWidth(290);
                mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                contentView.findViewById(R.id.pop_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status = "all";
                        mPopWindow.dismiss();
                    }
                });

                contentView.findViewById(R.id.pop_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status = "yes";
                        mPopWindow.dismiss();
                    }
                });
                contentView.findViewById(R.id.pop_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status = "no";
                        mPopWindow.dismiss();
                    }
                });
                mPopWindow.showAsDropDown(imageView, 0, 0);//showAsDropDown(View anchor，int xoff ,int yoff);
            }
        });
    }

    void dis() {
        Swip.setRefreshing(false);
    }

    void Toast(String str) {
        Toast.makeText(Main2Activity.this, str, Toast.LENGTH_SHORT).show();
    }

}
