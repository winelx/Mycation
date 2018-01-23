package com.newdemo.winelx.greedao.db;

import android.content.Context;

import com.newdemo.winelx.greedao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case 1:
//                UserDao.createTable(db, true);
                // 加入新字段 score
//                db.execSQL("ALTER TABLE 'STUDENT' ADD 'User' TEXT;");
                break;
        }

    }
}
