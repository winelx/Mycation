package com.newdemo.winelx.greedao;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.newdemo.winelx.greedao.Adapter.MyAdapter;
import com.newdemo.winelx.greedao.Bean.User;
import com.newdemo.winelx.greedao.Utils.UserHelper;
import com.newdemo.winelx.greedao.db.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_add, bt_delete, bt_update, bt_query;
    private ListView lv_content;
    private static int i = 10;
    private Context mContext;
    private UserHelper mHelper;
    private List<User> mstudent;
    private BaseAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mstudent = new ArrayList<>();
        mHelper = DbUtil.getDriverHelper();
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_query = (Button) findViewById(R.id.bt_query);
        lv_content = (ListView) findViewById(R.id.lv_content);

        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
//读取所有
        mstudent = mHelper.queryAll();
        //把学生信息显示到界面
        for (User s : mstudent) {
            User item = new User();
            item.id = s.getId();
            item.name = s.getName();
            item.number = s.getNumber();
            mstudent.add(item);
        }
        mAdapter = new MyAdapter<User>(mstudent, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, User obj) {
                Long str = obj.getId();
                holder.setText(R.id.item_id, str + "");
                holder.setText(R.id.item_user, obj.getName());
                holder.setText(R.id.item_number, obj.getNumber());
            }
        };
        lv_content.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                long id = mHelper.count();
                //界面添加
                User us = new User();
                us.id = id + 1;
                us.name = "Nauto_" + (id + 1);
                us.number = 6 + "" + (id + 1);
                //保存到数据库
                mHelper.save(us);
                break;
            case R.id.bt_delete:

                break;
            case R.id.bt_update:

                break;
            case R.id.bt_query:

                break;
        }
    }

}
