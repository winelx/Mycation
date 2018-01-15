package com.newdemo.winelx.greedao.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newdemo.winelx.greedao.Bean.Shop;
import com.newdemo.winelx.greedao.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class ShopListAdapter extends BaseAdapter {

    private List<Shop> list;
    private LayoutInflater mInflater;

    public ShopListAdapter(Context context, List<Shop> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_shop_list, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Shop shop = list.get(position);
        holder.tv_name.setText(shop.getName());
        holder.tv_price.setText(shop.getPrice() + "");
        holder.tv_price_discount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_sell_num.setText("已售" + shop.getSell_num() + "件");
        holder.tv_sell_num.setText("已售" + shop.getSell_num() + "件");
        return convertView;
    }

    /**
     * 获取控件管理对象
     */
    private ViewHolder getViewHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class ViewHolder {
        private TextView tv_name, tv_price, tv_price_discount, tv_sell_num;

        ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_price = view.findViewById(R.id.tv_price);
            tv_price_discount = view.findViewById(R.id.tv_price_discount);
            tv_sell_num = view.findViewById(R.id.tv_sell_num);
        }
    }

}
