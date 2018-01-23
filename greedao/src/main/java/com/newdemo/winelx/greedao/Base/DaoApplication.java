package com.newdemo.winelx.greedao.Base;

import android.app.Application;

import com.newdemo.winelx.greedao.db.DbCore;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class DaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        DbCore.init(this);
        DbCore.enableQueryBuilderLog(); //开启调试 log
    }
}
