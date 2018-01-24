package com.newdemo.winelx.myapplication;

import java.util.List;

/**
 * Created by 10942 on 2018/1/21 0021.
 */

public class bean {
    List<String> url;

    public bean(List<String> url) {
        this.url = url;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
