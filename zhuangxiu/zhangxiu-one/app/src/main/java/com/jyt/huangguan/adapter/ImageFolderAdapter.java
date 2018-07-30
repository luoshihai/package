package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.FolderThumbViewHolder;

/**
 * Created by chenweiqi on 2017/1/9.
 */

public class ImageFolderAdapter extends BaseRcvAdapter {


    BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FolderThumbViewHolder holder= new FolderThumbViewHolder(parent);
        holder.setOnViewHolderClickListener(onViewHolderClickListener);
        return holder;
    }


    public void setOnViewHolderClickListener(BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener) {
        this.onViewHolderClickListener = onViewHolderClickListener;
    }
}
