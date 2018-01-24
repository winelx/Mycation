package com.newdemo.winelx.daynight;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.newdemo.winelx.daynight.Utils.ConfigUtil;


/**
 * Created by leo on 16/4/24.
 */
public class AppContext extends Application {
    private static String THEME_KEY = "theme_mode";//这个是sp里保存的状态key值
    private static AppContext appContext;//单例实现appContext，
    private boolean isNight;//这个是当前状态值

    public static AppContext me() {
        if (appContext == null) {
            appContext = new AppContext();
        }
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initThemeMode();//调用方法进行设置
    }

    //这是默认调用的方法，根据保存的记录判断加载白天模式还是夜间模式
    private void initThemeMode() {
        //拿到sp里保存的状态值
        isNight = ConfigUtil.getBoolean(THEME_KEY, false);
        if (isNight) {//根据状态进行模式切换
            //夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
//这是切换的方法，
    public void setTheme(AppCompatActivity activity, boolean mode) {
        if (isNight == mode) {
            return;
        }
        if (!mode) {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        isNight = mode;
        ConfigUtil.putBoolean(THEME_KEY, isNight);
        activity.recreate();
    }

    /**
     * 刷新UI_MODE模式
     */
    public void refreshResources(Activity activity) {
        isNight = ConfigUtil.getBoolean(THEME_KEY, false);
        if (isNight) {
            updateConfig(activity, Configuration.UI_MODE_NIGHT_YES);
        } else {
            updateConfig(activity, Configuration.UI_MODE_NIGHT_NO);
        }
    }


    /**
     * google官方bug，暂时解决方案
     * 手机切屏后重新设置UI_MODE模式（因为在dayNight主题下，切换横屏后UI_MODE会出错，会导致资源获取出错，需要重新设置回来）
     */
    private void updateConfig(Activity activity, int uiNightMode) {
        Configuration newConfig = new Configuration(activity.getResources().getConfiguration());
        newConfig.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        newConfig.uiMode |= uiNightMode;
        activity.getResources().updateConfiguration(newConfig, null);
    }
}
