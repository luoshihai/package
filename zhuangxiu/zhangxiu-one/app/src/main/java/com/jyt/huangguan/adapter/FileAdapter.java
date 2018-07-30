package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.FileViewHolder;

/**
 * @author LinWei on 2017/11/28 11:24
 */
public class FileAdapter extends BaseRcvAdapter{
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FileViewHolder holder =new FileViewHolder(parent);
        holder.setOnViewHolderClickListener(onViewHolderClickListener);
        return holder;
    }
}
