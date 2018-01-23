package com.newdemo.winelx.greedao.db;


import com.newdemo.winelx.greedao.UserDao;
import com.newdemo.winelx.greedao.Utils.UserHelper;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class DbUtil {
    private static UserHelper suserHelper;
    private static UserDao getDriverDao() {
        return DbCore.getDaoSession().getUserDao();
    }
    public static UserHelper getDriverHelper() {
        if (suserHelper == null) {
            suserHelper = new UserHelper(getDriverDao());
        }
        return suserHelper;
    }

}
