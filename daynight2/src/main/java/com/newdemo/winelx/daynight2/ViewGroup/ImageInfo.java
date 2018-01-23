package com.newdemo.winelx.daynight2.ViewGroup;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23 0023.
 */

public class ImageInfo implements Serializable {
    public String imageUrl;
    public int imageViewHeight;
    public int imageViewWidth;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageViewHeight() {
        return imageViewHeight;
    }

    public void setImageViewHeight(int imageViewHeight) {
        this.imageViewHeight = imageViewHeight;
    }

    public int getImageViewWidth() {
        return imageViewWidth;
    }

    public void setImageViewWidth(int imageViewWidth) {
        this.imageViewWidth = imageViewWidth;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", imageViewHeight=" + imageViewHeight +
                ", imageViewWidth=" + imageViewWidth +
                '}';
    }
}