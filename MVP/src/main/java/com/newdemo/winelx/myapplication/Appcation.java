package com.newdemo.winelx.myapplication;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class Appcation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
    }
}
