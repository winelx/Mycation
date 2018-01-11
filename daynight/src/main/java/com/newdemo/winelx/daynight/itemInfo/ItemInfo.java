package com.newdemo.winelx.daynight.itemInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class ItemInfo implements Serializable {
    private String text;

    public ItemInfo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "text='" + text + '\'' +
                '}';
    }
}

