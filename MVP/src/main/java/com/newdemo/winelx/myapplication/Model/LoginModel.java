package com.newdemo.winelx.myapplication.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.newdemo.winelx.myapplication.Main2Activity;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class LoginModel implements LogModel {
    ProgressDialog waitingDialog;

    @Override
    public void Login(final Context context, String user, String pass) {
        waitingDialog =
                new ProgressDialog(context);
        waitingDialog.setMessage("...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
        OkGo.<String>post("http://www.wanandroid.com/user/login")
                .params("username", user)
                .params("password", pass)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        context.startActivity(new Intent(context, Main2Activity.class));
                        waitingDialog.dismiss();
                    }
                });

    }


}
