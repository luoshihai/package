package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.ShowImageViewHolder;

/**
 * Created by chenweiqi on 2017/10/30.
 */

public class ShowImageAdapter  extends BaseRcvAdapter{
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShowImageViewHolder holder = new ShowImageViewHolder(parent);
        holder.setOnViewHolderClickListener(onViewHolderClickListener);
        holder.setOnViewHolderLongClickListener(onViewHolderLongClickListener);
        return holder;
    }
}
