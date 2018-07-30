package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.ConstructionViewHolder;
import com.jyt.huangguan.view.widget.TitleAndFlowImages;

/**
 * @author LinWei on 2017/12/4 16:01
 */
public class ConstructionAdapter extends BaseRcvAdapter{
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstructionViewHolder holder =new ConstructionViewHolder(parent);
        holder.setOnImageClickListener(onImageClickListener);
        return holder;
    }

    private TitleAndFlowImages.OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(TitleAndFlowImages.OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

}
