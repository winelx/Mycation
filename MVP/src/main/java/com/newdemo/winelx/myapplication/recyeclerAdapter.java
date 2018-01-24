package com.newdemo.winelx.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10942 on 2018/1/21 0021.
 */

public class recyeclerAdapter extends RecyclerView.Adapter<recyeclerAdapter.ViewHolder> {
    private Context mContext;
    private List<bean> mData;
    ImageLoader mImageLoader;

    public recyeclerAdapter(Context context, List<bean> mData) {
        this.mContext = context;
        this.mData = mData;
        RequestQueue queue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(queue, new BitmapCache());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.adapter, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        List<String> pos = new ArrayList<>();

        pos.addAll(mData.get(position).getUrl());
        if (pos.size() == 0) {
            holder.imageView.setVisibility(View.GONE);
            holder.imageView1.setVisibility(View.GONE);
            holder.imageView2.setVisibility(View.GONE);
        } else {
            if (pos.size() >= 1) {
                holder.imageView.setImageUrl(pos.get(0), mImageLoader);
                if (pos.size() >= 2) {
                    holder.imageView1.setImageUrl(pos.get(1), mImageLoader);
                    if (pos.size() >= 3) {
                        holder.imageView2.setImageUrl(pos.get(2), mImageLoader);
                        ;
                        ;
                    }
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageView, imageView1, imageView2;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_image);
            imageView1 = itemView.findViewById(R.id.rec_image2);
            imageView2 = itemView.findViewById(R.id.rec_image3);

        }
    }

    /**
     * 使用LruCache来缓存图片
     */
    public class BitmapCache implements com.android.volley.toolbox.ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            // 获取应用程序最大可用内存
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheSize = maxMemory / 8;
            mCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }
}
