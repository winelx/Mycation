package com.newdemo.winelx.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by 10942 on 2018/1/21 0021.
 */

public class NetWorkImageView extends android.support.v7.widget.AppCompatImageView {
    private final static String TAG = "NetWorkImageView";
    private static AsyncImageLoader asyncImageLoader;
    private Context context;

    public NetWorkImageView(Context context) {
        this(context, null);
    }

    public NetWorkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetWorkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSth(context);
    }

    private void initSth(Context context) {
        this.context = context;
        if (asyncImageLoader == null) {
            asyncImageLoader = new AsyncImageLoader(context);
        }
    }

    public void loadImage(String imgUrl, final int defaultRes) {
        if (TextUtils.isEmpty(imgUrl)) {
            setImageResource(defaultRes);// 调用ImageView中默认的设置图片方法
            return;
        }
        Log.d(TAG, "load imgUrl is :" + imgUrl);
        Drawable d = asyncImageLoader.loadDrawable(imgUrl,
                new AsyncImageLoader.ImageLoadCallback() {

                    @Override
                    public void imageLoaded(Drawable drawable, String imgUrl) {
                        Object tag = getTag(); // 获取NetWorkImageView设置的Tag值，比对如果有则不再重新加载
                        if (tag != null && (tag instanceof String)
                                && !tag.toString().equals(imgUrl)) {
                            return;
                        }
                        if (context instanceof Activity) { // 如果当前Activity被结束了，则不加载图片
                            if (((Activity) context).isFinishing()) {
                                return;
                            }
                        }
                        // 设置显示图片
                        setImage(drawable, defaultRes);
                    }
                });
        setImage(d, defaultRes);
    }

    // 显示图片
    private void setImage(Drawable drawable, int defaultRes) {
        if (drawable != null) {
            setImageDrawable(drawable);
            return;
        }
        if (defaultRes != -1) {
            setImageResource(defaultRes);
        }

    }

    public void loadImage(String imgUrl) {
        loadImage(imgUrl, -1);
    }

    /**
     * 取消加载图片
     */
    public void cancelTask() {
        asyncImageLoader.shutDownTread();
    }

}