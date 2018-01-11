package com.newdemo.winelx.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.newdemo.winelx.myapplication.Presenter.LoginPersen;
import com.newdemo.winelx.myapplication.View.UIVIew;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UIVIew{
    private EditText user, password;
    private LoginPersen mLoginPersen;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        mLoginPersen = new LoginPersen();
        findViewById(R.id.input).setOnClickListener(this);
        findViewById(R.id.output).setOnClickListener(this);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input:
                mLoginPersen.Login(context, getUser(), getPass());
                break;
            case R.id.output:
                context.startActivity(new Intent(context, Main2Activity.class));
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
