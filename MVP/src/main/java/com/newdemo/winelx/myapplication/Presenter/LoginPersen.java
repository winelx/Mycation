package com.newdemo.winelx.myapplication.Presenter;

import android.content.Context;

import com.newdemo.winelx.myapplication.Model.LogModel;
import com.newdemo.winelx.myapplication.Model.LoginModel;


/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class LoginPersen {
    private LogModel logModel;

    public LoginPersen() {
        logModel = new LoginModel();
    }

    public void Login(Context context,String user, String pass) {
        logModel.Login(context,user, pass);
    }

}
