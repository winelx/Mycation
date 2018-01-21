package com.newdemo.winelx.greedao.Base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.newdemo.winelx.greedao.Bean.DaoMaster;
import com.newdemo.winelx.greedao.Bean.DaoSession;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class BaseApplication  extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}