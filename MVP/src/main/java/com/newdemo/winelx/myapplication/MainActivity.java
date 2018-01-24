package com.newdemo.winelx.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.newdemo.winelx.myapplication.Presenter.LoginPersen;
import com.newdemo.winelx.myapplication.View.UIVIew;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UIVIew {
    private EditText user, password;
    private LoginPersen mLoginPersen;
    private Context context;
    private ArrayList<String> path;
    private ListView mListView;
    private MyAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        mListView = findViewById(R.id.lsit);
        path = new ArrayList<>();
        path.add("https://b-ssl.duitang.com/uploads/item/201306/24/20130624112720_BtK8T.thumb.700_0.jpeg");
        path.add("https://b-ssl.duitang.com/uploads/item/201306/17/20130617092916_Xe8BZ.thumb.700_0.jpeg");
        path.add("https://b-ssl.duitang.com/uploads/item/201306/19/20130619090708_vxHuz.thumb.700_0.jpeg");
        path.add("https://b-ssl.duitang.com/uploads/item/201306/16/20130616101943_eejPd.thumb.700_0.jpeg");
        path.add("https://b-ssl.duitang.com/uploads/item/201306/13/20130613090440_5JXjK.thumb.700_0.jpeg");
        path.add("https://b-ssl.duitang.com/uploads/item/201306/13/20130613090339_C4L2A.thumb.700_0.jpeg");
        mLoginPersen = new LoginPersen();
        findViewById(R.id.input).setOnClickListener(this);
        findViewById(R.id.output).setOnClickListener(this);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        mAdapter = new MyAdapter<String>(path, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageGlide(R.id.img_icon, obj);
            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("data", path);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input:
                mLoginPersen.Login(context, getUser(), getPass());
                break;
            case R.id.output:
                context.startActivity(new Intent(context, Main22Activity.class));
                break;
            default:
                break;
        }
    }


    @Override
    public String getUser() {
        return user.getText().toString();
    }

    @Override
    public String getPass() {
        return password.getText().toString();
    }
}
