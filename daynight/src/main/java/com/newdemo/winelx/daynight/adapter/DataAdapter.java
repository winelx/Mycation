package com.newdemo.winelx.daynight.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newdemo.winelx.daynight.R;
import com.newdemo.winelx.daynight.itemInfo.ItemInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHodler> {
    private List<ItemInfo> itemInfos;

    public DataAdapter(List<ItemInfo> itemInfos) {
        this.itemInfos = itemInfos;
    }

    @Override
    public DataHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        return new DataHodler(view);
    }

    @Override
    public void onBindViewHolder(DataHodler holder, int position) {
        holder.setData(itemInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return itemInfos.size();
    }

    public class DataHodler extends RecyclerView.ViewHolder {

        @Bind(R.id.item_text)
        TextView itemText;

        public DataHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ItemInfo info) {
            itemText.setText(info.getText() + "");
        }
    }
}

