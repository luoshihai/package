package com.jyt.huangguan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jyt.huangguan.bean.LocalMedia;
import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.ImageThumbViewHolder;

import java.util.List;

/**
 * Created by chenweiqi on 2017/1/9.
 */

public class ImageThumbAdapter extends BaseRcvAdapter {

    BaseViewHolder.OnViewHolderClickListener onCheckImageClickListener;
    BaseViewHolder.OnViewHolderClickListener onBrowseImageClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageThumbViewHolder holder = new ImageThumbViewHolder(parent);
        holder.setOnBrowseImageClickListener(onBrowseImageClickListener);
        holder.setOnCheckImageClickListener(onCheckImageClickListener);
        return  holder;

    }

    public void setOnCheckImageClickListener(BaseViewHolder.OnViewHolderClickListener onCheckImageClickListener) {
        this.onCheckImageClickListener = onCheckImageClickListener;
    }

    public void setOnBrowseImageClickListener(BaseViewHolder.OnViewHolderClickListener onBrowseImageClickListener) {
        this.onBrowseImageClickListener = onBrowseImageClickListener;
    }


}
