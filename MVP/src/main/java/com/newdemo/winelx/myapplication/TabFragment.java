package com.newdemo.winelx.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {
    private int pos;
    private TabAdapter tabAdapter;

    @SuppressLint("ValidFragment")
    public TabFragment(int pos) {
        this.pos = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag, container, false);
        ImageView imageView = view.findViewById(R.id.img);
        Glide.with(getActivity()).load(tabAdapter.mData.get(pos)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        return view;
    }
}
