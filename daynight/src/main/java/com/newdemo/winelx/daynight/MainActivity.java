package com.newdemo.winelx.daynight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.newdemo.winelx.daynight.adapter.DataAdapter;
import com.newdemo.winelx.daynight.itemInfo.ItemInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 换肤方式宜
 */
public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_list)
    RecyclerView rvList;

    private DataAdapter dataAdapter;
    private List<ItemInfo> itemInfos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addData();
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(this);
        dataAdapter = new DataAdapter(itemInfos);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(dataAdapter);
    }
    private void addData() {
        for (int i = 0; i < 20; i++) {
            itemInfos.add(new ItemInfo("测试数据" + i));
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.day:
                AppContext.me().setTheme(this, false);
                break;
            case R.id.night:
                AppContext.me().setTheme(this, true);
                break;
            case R.id.second_activity:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.third_activity:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
        }
        return false;
    }
    @Override
    protected void onResume() {
        AppContext.me().refreshResources(this);
        super.onResume();
    }
}
