package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.ManeuverViewHolder;

/**
 * @author LinWei on 2017/11/27 17:43
 */
public class ManeuverAdapter extends BaseRcvAdapter{
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ManeuverViewHolder holder = new ManeuverViewHolder(parent);
        holder.setOnViewHolderClickListener(onViewHolderClickListener);
        return holder;
    }


}
